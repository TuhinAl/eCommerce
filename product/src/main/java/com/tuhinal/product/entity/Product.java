package com.tuhinal.product.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "product_info")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonFormat
public class Product extends Auditable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

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


    public Product(String id) {
        this.id = id;
    }
}
