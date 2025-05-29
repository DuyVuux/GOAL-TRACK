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

- **Search & Filter**  
  Tìm kiếm highlight/trận đấu theo đội, ngày, giải đấu. Hỗ trợ full-text search.

- **Communication Module**  
  Gửi email thông báo và thông báo thời gian thực (WebSocket).

- **API & Documentation**  
  Sinh tài liệu API tự động bằng Swagger/OpenAPI. Hỗ trợ versioning endpoint.

- **Logging & Analytics**  
  Ghi log hệ thống, thống kê truy cập, phân tích hành vi người dùng và highlight.

- **Scheduler & Automation**  
  Cron job định kỳ để cập nhật dữ liệu, xoá cache, và backup dữ liệu.

---

## 🚀 Yêu cầu phi chức năng

- **Hiệu năng**: 100+ người dùng đồng thời, trang chủ tải <2s, trang highlight <3s, sử dụng cache.
- **Bảo mật**: Mật khẩu mã hóa (bcrypt), phân quyền rõ ràng, HTTPS, JWT, kiểm thử bảo mật định kỳ.
- **Khả năng mở rộng**: Thiết kế module hóa, hỗ trợ tăng lượng user và dữ liệu.
- **Dễ bảo trì**: Code rõ ràng, tài liệu đầy đủ, backup định kỳ.
- **Phục hồi**: Tự động backup và khôi phục nhanh khi có sự cố.
- **Giao diện**: Đơn giản, dễ dùng, hỗ trợ mobile, ngôn ngữ mặc định: tiếng Việt (tiếng Anh sẽ bổ sung).

---

## 🏗️ Kiến trúc & Công nghệ

| Thành phần  | Công nghệ sử dụng |
|-------------|------------------|
| **Frontend** | React.js |
| **Backend** | Spring Boot 3.x, Spring Security, JWT, Swagger/OpenAPI, Spring Cache (Redis), Spring Scheduler |
| **Database** | PostgreSQL (chính), Redis (cache) |
| **Lưu trữ file** | AWS S3 / Google Cloud Storage (video highlight) |
| **DevOps** | Docker Compose, CI/CD (GitHub Actions, GitLab CI), giám sát với Prometheus, Grafana |
| **Bảo mật** | HTTPS, bcrypt, kiểm thử tự động |

---

## 📏 Tiêu chí chất lượng

- **Độ tin cậy**: Uptime ≥ 99.9%, MTBF ≥ 1000h, MTTR ≤ 5 phút
- **Chính xác dữ liệu**: ≥ 99.5%, xử lý lỗi ≤ 24h
- **Trải nghiệm người dùng**: Usability score ≥ 85%, thao tác thành công ≤ 3 lần thử
- **Khả năng chịu tải**: ≥ 1000 requests/second, phục hồi nhanh khi gặp lỗi

---

## 🏁 Tầm nhìn

**GoalTrack** hướng tới trở thành nền tảng **tập trung, tiện lợi và mở rộng dễ dàng** cho người hâm mộ bóng đá và các nhà phát triển — giúp truy cập, quản lý và khai thác dữ liệu bóng đá cũng như video highlight **hiệu quả, an toàn và linh hoạt**.

---

> 🔗 *Có thể tích hợp frontend hoặc app di động một cách đơn giản thông qua các API công khai đã được version hóa.*
