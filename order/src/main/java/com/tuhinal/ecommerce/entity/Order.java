package com.tuhinal.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "order_info")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonFormat
public class Order extends Auditable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "sku_code")
    private String skuCode;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "quantity")
    private Integer quantity;

/*    @Column(name = "attendance_Leave_type_key") // Check-In/Check-Out
    @Enumerated(EnumType.STRING)
    private AttendanceEnum attendanceLeaveTypeKey;*/

    //todo will add overtime business/ will add overtime button
    /*@Column(name = "early_leave_minutes")
    private Double earlyLeaveMinutes;*/

/*    @Column(name = "date_at")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateAt;*/


    public Order(String id) {
        this.id = id;
    }
}
