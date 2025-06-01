# ⚽ GoalTrack

**GoalTrack** là hệ thống backend quản lý **dữ liệu bóng đá** và **video highlight**, được phát triển bằng **Spring Boot**, hướng tới mục tiêu **tập trung hóa thông tin bóng đá** và **tối ưu trải nghiệm người dùng** trong việc truy cập highlight trận đấu.

---

## 🎯 Mục đích

- Thu thập và hiển thị dữ liệu bóng đá (tỉ số, lịch đấu, đội bóng, trận đấu, ...) từ **API bên thứ 3**.
- Cho phép người dùng **nội bộ hoặc được phân quyền** đăng tải, quản lý video highlight các trận đấu.

---

## 👥 Đối tượng sử dụng

| Vai trò             | Mô tả                                                                 |
|---------------------|------------------------------------------------------------------------|
| 👁️ Viewer           | Xem thông tin bóng đá qua API công khai.                               |
| 📤 Content Uploader | Đăng tải video highlight (admin nội bộ hoặc user được cấp quyền).       |
| ⚙️ System Admin     | Quản trị toàn bộ hệ thống, dữ liệu highlight và cấu hình API tích hợp. |
| 👨‍💻 Developer       | Tích hợp API để xây dựng frontend hoặc ứng dụng khác.                   |
| 🔌 API Client        | Hệ thống khác truy vấn dữ liệu bóng đá từ GoalTrack qua API.           |

---

## 🧩 Chức năng chính

- **Football Data Module**  
  Thu thập, lưu trữ và hiển thị lịch đấu, tỉ số, bảng xếp hạng từ API bên thứ 3. Hỗ trợ cache để giảm tải.

- **Highlight Management**  
  Quản lý video highlight: upload, xem, sửa, xoá, phân quyền truy cập. Tích hợp lưu trữ đám mây (AWS S3, Google Cloud).

- **User & Auth Module**  
  Xác thực (JWT/OAuth), phân quyền (admin/user/internal), quản lý session, giới hạn tần suất truy cập API.

- **Admin Dashboard**  
  Quản lý người dùng, kiểm tra hệ thống (logs, audit), xử lý dữ liệu khẩn cấp.

[//]: # (- **Search & Filter**  )

[//]: # (  Tìm kiếm highlight/trận đấu theo đội, ngày, giải đấu. Hỗ trợ full-text search.)

[//]: # ()
[//]: # (- **Communication Module**  )

[//]: # (  Gửi email thông báo và thông báo thời gian thực &#40;WebSocket&#41;.)

[//]: # ()
[//]: # (- **API & Documentation**  )

[//]: # (  Sinh tài liệu API tự động bằng Swagger/OpenAPI. Hỗ trợ versioning endpoint.)

[//]: # ()
[//]: # (- **Logging & Analytics**  )

[//]: # (  Ghi log hệ thống, thống kê truy cập, phân tích hành vi người dùng và highlight.)

[//]: # ()
[//]: # (- **Scheduler & Automation**  )

[//]: # (  Cron job định kỳ để cập nhật dữ liệu, xoá cache, và backup dữ liệu.)

[//]: # ()
[//]: # (---)

## 🏗️ Kiến trúc & Công nghệ

| Thành phần  | Công nghệ sử dụng                                     |
|-------------|-------------------------------------------------------|
| **Frontend** | Đang hoàn thiện                                       |
| **Backend** | Spring Boot 3.x, Spring Security, JWT, Swagger/OpenAP |
| **Database** | PostgreSQL (chính)                                    |

[//]: # (| **Lưu trữ file** | AWS S3 / Google Cloud Storage &#40;video highlight&#41;       |)

[//]: # (| **DevOps** | Docker Compose                                        |)

[//]: # (| **Bảo mật** | HTTPS                                                 |)

---

## 🏁 Tầm nhìn

**GoalTrack** hướng tới trở thành nền tảng **tập trung, tiện lợi và mở rộng dễ dàng** cho người hâm mộ bóng đá và các nhà phát triển — giúp truy cập, quản lý và khai thác dữ liệu bóng đá cũng như video highlight **hiệu quả, an toàn và linh hoạt**.

---

[//]: # (> 🔗 *Có thể tích hợp frontend hoặc app di động một cách đơn giản thông qua các API công khai đã được version hóa.*)
