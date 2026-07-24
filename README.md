# GES (Get Enough Sleep) 🌙

Một ứng dụng Android được thiết kế theo hướng Gamification (Trò chơi hóa) nhằm cải thiện thói quen thức khuya. Ứng dụng giúp người dùng duy trì kỷ luật giấc ngủ bằng cách giám sát trạng thái màn hình, hạn chế sự cám dỗ từ các vòng lặp nội dung ngắn (Short-form content) vào ban đêm.

## 🚀 Tính năng cốt lõi (MVP)

*   **Giám sát trạng thái màn hình (Screen State Tracking):** Ứng dụng theo dõi trực tiếp các sự kiện `ACTION_SCREEN_OFF` và `ACTION_SCREEN_ON` thông qua Background/Foreground Service để tính toán thời gian ngủ thực tế.
*   **Gamification (Trò chơi hóa):** Biến việc đi ngủ thành một nhiệm vụ. Nếu người dùng mở khóa màn hình trong khung giờ giới hạn, tiến trình giấc ngủ sẽ bị đánh dấu là "Thất bại".
*   **Tối ưu hóa hệ thống (HyperOS/MIUI Compatibility):** Xử lý luồng xin quyền Autostart và vô hiệu hóa Battery Restrictions để đảm bảo Service không bị hệ điều hành đóng băng khi chạy ngầm.

## 🛠 Tech Stack

*   **Ngôn ngữ:** Kotlin
*   **UI Framework:** Jetpack Compose (Declarative UI)
*   **Core Components:** Foreground Services, Broadcast Receivers, Intents
*   **Build System:** Gradle (Kotlin DSL)

## ⚙️ CI/CD Pipeline (GitHub Actions)

Dự án được tích hợp sẵn luồng tự động hóa (Continuous Integration/Continuous Deployment) để tối ưu hóa quá trình kiểm thử:
*   Mỗi khi mã nguồn được push lên nhánh `main`, **GitHub Actions** sẽ tự động khởi tạo môi trường (Ubuntu + JDK 17).
*   Thực thi quá trình biên dịch (Build) và đóng gói mã nguồn.
*   Xuất file `app-debug.apk` trực tiếp tại tab **Actions -> Artifacts**, cho phép tải về và cài đặt lên thiết bị thật mà không cần build thủ công qua IDE.

## 📦 Hướng dẫn cài đặt (Dành cho Tester/User)

1. Truy cập tab **Actions** trên Repository này.
2. Chọn workflow build mới nhất (có dấu tích xanh ✅).
3. Cuộn xuống phần **Artifacts** và tải file `GES-app-debug.zip`.
4. Giải nén, chép file `.apk` vào điện thoại Android.
5. Cài đặt và cấp quyền **Hiển thị trên các ứng dụng khác** & **Bỏ qua tối ưu hóa pin** ở lần chạy đầu tiên.

---
*Developed by [DUTVcore](https://github.com/DUTVcore)*