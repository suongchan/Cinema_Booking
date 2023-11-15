package com.example.cinema_booking.controller.CustomerController;


import com.example.cinema_booking.domain.Order;
import com.example.cinema_booking.entity.*;
import com.example.cinema_booking.exception.CustomerNotFoundException;
import com.example.cinema_booking.repository.*;
import com.example.cinema_booking.service.*;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("customer")
public class OrderController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ShowtimeService showtimeService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailTicketRepository orderDetailTicketRepository;

    @Autowired
    private OrderDetailServiceRepository orderDetailServiceRepository;

    @Autowired
    private SeatStatusService seatStatusService;

    @GetMapping("checkout/{id}")
    public String processCheckout(@PathVariable Long id, Principal principal, Model model) throws CustomerNotFoundException {
        String username = principal.getName(); // Lấy tên người dùng hiện tại
        CustomerEntity customer = customerService.getCustomerByUsername(username);

        Long customerId = customer.getIdCustomer();
        ShowtimeEntity showtime = showtimeService.getShowtimeById(id);

        model.addAttribute("customer", customer);
        model.addAttribute("showtime", showtime);
//        model.addAttribute("showtime", showtime);

        // Thêm các thông tin dịch vụ và thông tin khác nếu cần

        // Trả về trang `checkout`
        return "customerHtml/checkout";
    }

//    @PostMapping("/createOrder")
//    public ResponseEntity<?> createOrder(@RequestBody Map<String, Object> orderData) {
//        try {
//            // Parse data from the request
//            Long totalPrice = Long.parseLong(orderData.get("totalPrice").toString());
//            Long customerId = Long.parseLong(orderData.get("customerId").toString());
//            Long showtimeId = Long.parseLong(orderData.get("showtimeId").toString());
//            String nameTicket = orderData.get("nameTicket").toString();
//
//            // Create an OrderEntity
//            OrderEntity orderEntity = new OrderEntity();
//            orderEntity.setOrderDateTime(LocalDateTime.now());
//            orderEntity.setTotalPrice(totalPrice);
//
//            // Retrieve and set the ShowtimeEntity
//            ShowtimeEntity showtime = showtimeService.getShowtimeById(showtimeId);
//            orderEntity.setShowtimeEntity(showtime);
//
//            // Retrieve and set the CustomerEntity
//            CustomerEntity customer = customerService.getCustomerById(customerId);
//            orderEntity.setCustomerEntity(customer);
//
//            // Save the OrderEntity to the database
//            orderService.createOrder(orderEntity);
//
//            return ResponseEntity.ok("Order created successfully");
//        } catch (NumberFormatException e) {
//            // Handle the case where parsing fails (e.g., invalid data format)
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data format");
//        } catch (Exception e) {
//            // Log the exception for debugging purposes
//            e.printStackTrace(); // You can replace this with proper logging
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating order");
//        }
//    }

    @PostMapping("/createOrder")
    public ResponseEntity<String> createOrder(@RequestBody Map<String, Object> orderData) {
        try {
            Long totalPrice = Long.parseLong(orderData.get("totalPrice").toString());
            Long customerId = Long.parseLong(orderData.get("customerId").toString());
            Long showtimeId = Long.parseLong(orderData.get("showtimeId").toString());
            List<String> selectedChairs = (List<String>) orderData.get("selectedChairs");
            System.out.println(totalPrice);
            System.out.println(selectedChairs);
            System.out.println(".....................");

            // Create and save the OrderEntity
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderDateTime(LocalDateTime.now());
            orderEntity.setTotalPrice(totalPrice);

            // Retrieve and set the ShowtimeEntity
            ShowtimeEntity showtime = showtimeService.getShowtimeById(showtimeId);
            orderEntity.setShowtimeEntity(showtime);

            // Retrieve and set the CustomerEntity
            CustomerEntity customer = customerService.getCustomerById(customerId);
            orderEntity.setCustomerEntity(customer);

            // Save the OrderEntity to the database
            orderService.createOrder(orderEntity);

            for (String seat : selectedChairs) {
                System.out.println(seat + "/");

                SeatStatusEntity seatStatus = seatStatusService.getSeats(showtimeId, seat);
                if (seatStatus != null) {
                    System.out.println("Seat Status: " + seatStatus);

                    // Create and save OrderDetailTicketEntity
                    OrderDetailTicketEntity orderDetailTicket = new OrderDetailTicketEntity();
                    orderDetailTicket.setSeatStatusEntity(seatStatus);
                    orderDetailTicket.setOrderEntity(orderEntity);
                    orderDetailTicket.setShowtimeEntity(showtime);
                    orderDetailTicketRepository.save(orderDetailTicket);
                } else {
                    System.out.println("Seat Status not found for seat: " + seat);
                    // Handle the case where SeatStatusEntity is not found
                }
            }

            String checkoutNumberService = (String) orderData.get("checkoutNumberService");
            Pattern pattern = Pattern.compile("Số lượng: (\\d+) Dịch vụ: ([^\\d]+)");
            Matcher matcher = pattern.matcher(checkoutNumberService);

            List<Map<String, String>> results = new ArrayList<>();

// Sử dụng biểu thức chính quy để tìm các kết quả phù hợp trong chuỗi
            while (matcher.find()) {
                String numberService = matcher.group(1);
                String nameService = matcher.group(2).trim();

                // Lọc bỏ thông tin giá nếu có
                nameService = nameService.replace(" Giá:", "").trim();

                // Tạo một Map để lưu trữ dữ liệu và thêm vào danh sách kết quả
                Map<String, String> serviceData = Map.of("nameService", nameService, "numberService", numberService);
                results.add(serviceData);
                System.out.println(results);
            }

            for (Map<String, String> result : results) {
                String nameService = result.get("nameService");
                int numberService = Integer.valueOf(result.get("numberService"));

                // Tìm dịch vụ dựa trên tên
                ServiceEntity service = serviceService.getServiceByName(nameService);

                if (service != null) {
                    // Lưu thông tin chi tiết đặt hàng cho dịch vụ
                    OrderDetailServiceEntity orderDetailServiceEntity = new OrderDetailServiceEntity();
                    orderDetailServiceEntity.setServiceEntity(service);
                    System.out.println("______________");
                    System.out.println(service);
                    orderDetailServiceEntity.setOrderEntity(orderEntity);
                    orderDetailServiceEntity.setQuantity(numberService);
                    System.out.println(numberService);
                    orderDetailServiceRepository.save(orderDetailServiceEntity);
                } else {
                    // Xử lý trường hợp không tìm thấy dịch vụ
                    // (ví dụ: bằng cách hiển thị thông báo lỗi hoặc thực hiện xử lý khác)
                }
            }


        } catch (Exception e) {
            // Xử lý lỗi nếu cần
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating seat status");
        }



//
        return ResponseEntity.ok("Order created successfully");

    }



    @GetMapping("payment")
    public String paymentPage() {
//        String username = principal.getName(); // Lấy tên người dùng hiện tại
//        CustomerEntity customer = customerService.getCustomerByUsername(username);
//
//        Long customerId = customer.getIdCustomer();
//        model.addAttribute("customer", customer);
//
//        ShowtimeEntity showtime = showtimeService.getShowtimeById(id);
//        model.addAttribute("showtime", showtime);

        return "customerHtml/payment";
    }


}