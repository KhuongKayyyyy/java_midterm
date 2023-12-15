# Midterm Java Technology
-Đây là một web site xây dựng cho một website bán sách theo mô hình spring mvc.
-Tất cả các hình ảnh demo, mô hình quan hệ của website được chứa trong thư mục image
# Các công nghệ sử dụng trong bài cáo cáo
- MySql: quản lý database
- Thymlead: định dạng mẫu (template) HTML/XML được sử dụng để xây dựng trang web java.
- Lombok: giảm thiểu lượng mã lặp đi lặp lại bằng cách tạo ra các phương thức getter, setter, constructor tự động từ các annotation được định nghĩa trong lớp Java
- Hibernate: ánh xạ các đối tượng Java thành các bảng trong cơ sở dữ liệu.
- Spring boot web: dễ dàng phát triển web bằng mô hình MVC
# Mô hình ERD
![Database](./image/ERD.jpg)
# Cấu trúc dự án
![folderStructureBackEnd](./image/backEnd.jpg)
1. config: chứa file WebSecurityConfig.java nơi lưu trữ các cài đặt bảo mật của chương trình web.
2. controller: chứa các controller với các nhiệm vụ như sau:
- BookRestController.java có nhiệm vụ thực hiện các thao tác CRUD sử dụng API lên đối tượng Book
- BooksController.java : thực hiện các thao tác CRUD sử dụng trên giao diện chương trình (thực hiện bằng giao diện admin)
- ShoppingController.java: nơi thực hiện các thao tác điều hướng trên các trang mua sắm của chương trình, cũng như các yêu cầu của khách hàng (xem sản phẩm, chi tiết sản phẩm, thêm sản phẩm vào giỏ hàng, checkout hóa đơn, xem danh sách các hóa đơn của bản thân).
-UploadController.java: handle chức năng tải ảnh của Book về máy chủ để hiển thị trên website.
-Webcontroller.java: thực hiện các chức năng điều hướng chung như đăng nhập, đăng ký, đăng xuất.
3. model: chứa các đối tượng có mặt trong chương trình
- Books, Brand, Cart, Category, Order, OrderDetail, User: là các bảng trong Database được sử dụng thông qua Hibernate
- ResponseObject là đối tượng trả về giá trị cho API
4. Payroll: chứa file BooksNotFoundException.java dùng để handle việc Book không được tìm thấy.
5. repository: chứa các interface được kế thừa từ JPARepository để dùng tương tác với database
6. service: là tầng để tương tác giữa Controller và Repository
![folderStructureFrontend](./image/frontEnd.jpg)
1. Các template liên quan đến trang mua sắm được lưu trữ tại folder users.
2. Các template liên quan đến việc quản lý, CRUD Book của Admin được lưu trữ tại folder books
3. Các file css, hình ảnh, js được lưu trữ tại thư mục static.
# Các bước để chạy ứng dụng
- B1: Bật xampp (hoặc manage-osx trên Mac) và truy cập trang phpAdmin.
- B2: Tạo một database BookStore (hoặc tên bất kỳ) và tiến hành nhập file bookstore.sql vào database đó.
- B3: Tiến hành kiểm tra đường dẫn đến trang database trong file application.properties
- B4: Tiến hành chạy chương trình và truy cập đường dẫn http://localhost:8080/ trên trình duyệt.
- B5: Có thể sử dụng tài khoản user tạo sẵn (tk: test- pass:test1123) và tài khoản admin (tk: admin - pass: 123456) để kiểm thử trang web, hoặc có thể tạo mới tài khoản user tùy thích.
# Database
![database](bookstore.sql)
# API BRAND
- Thêm một Brand mới:
![image](./image/addBrand.jpg)
- Tìm kiếm tất cả các Brand:
![image](./image/getAllBrand.jpg)
- Tìm kiêm Brand bằng ID:
![image](./image/getBrandByID.jpg)
- Cập nhật Brand:
![image](./image/updateBrand.jpg)
- Xóa một Brand:
![image](./image/deleteBrand.jpg)
- Kết quả sau khi xóa Brand:
![image](./image/afterDeleteBrand.jpg)
# API BOOK:
- Thêm một sản phẩm:
![image](./image/addBook.jpg)
- Tìm kiếm tất cả các sản phẩm:
![image](./image/getAllBook.jpg)
- Tìm kiếm sản phâm theo ID:
![image](./image/getBookByID.jpg)
- Cập nhật sản phẩm:
![image](./image/updateBook.jpg)
- Xóa một sản phẩm:
![image](./image/deleteBook.jpg)
# Video demo chương trình
![demoVideo](https://drive.google.com/file/d/1M69COCEkTvCvqd91Pq3JnZ8NfMocIWzp/view?usp=sharing)
# Tài liệu báo cáo
![docs](52100973_NguyenDatKhuong.pdf)