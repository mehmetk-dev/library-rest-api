# 📚 Library REST API

Spring Boot ile geliştirilmiş, kitap ödünç alma (borrow), kategori, yazar, yayınevi ve kitap işlemlerini yöneten bir RESTful kütüphane uygulamasıdır.

## 📌 Base URL

http://localhost:8080/api


---

## 🔗 API Endpointleri

### 📘 Book (Kitap)

| HTTP  | Endpoint         | Açıklama                    |
|--------|------------------|-----------------------------|
| GET    | /books/{id}      | ID’ye göre kitap getirir    |
| GET    | /books           | Tüm kitapları listeler      |
| POST   | /books           | Yeni kitap ekler            |
| PUT    | /books           | Kitap bilgisi günceller     |
| DELETE | /books/{id}      | Kitap siler                 |

---

### 👤 Author (Yazar)

| HTTP  | Endpoint          | Açıklama                     |
|--------|-------------------|------------------------------|
| GET    | /authors/{id}     | ID’ye göre yazar getirir     |
| GET    | /authors          | Tüm yazarları listeler       |
| POST   | /authors          | Yeni yazar ekler             |
| PUT    | /authors          | Yazar bilgisi günceller      |
| DELETE | /authors/{id}     | Yazar siler                  |

---

### 🗂️ Category (Kategori)

| HTTP  | Endpoint             | Açıklama                         |
|--------|----------------------|----------------------------------|
| GET    | /categories/{id}     | ID’ye göre kategori getirir      |
| GET    | /categories          | Tüm kategorileri listeler        |
| POST   | /categories          | Yeni kategori ekler              |
| PUT    | /categories          | Kategori bilgisi günceller       |
| DELETE | /categories/{id}     | Kategori siler (eğer ilişkili kitap yoksa) |

---

### 🏢 Publisher (Yayınevi)

| HTTP  | Endpoint               | Açıklama                       |
|--------|------------------------|--------------------------------|
| GET    | /publishers/{id}       | ID’ye göre yayınevi getirir    |
| GET    | /publishers            | Tüm yayınevlerini listeler     |
| POST   | /publishers            | Yeni yayınevi ekler            |
| PUT    | /publishers            | Yayınevi bilgisi günceller     |
| DELETE | /publishers/{id}       | Yayınevi siler                 |

---

### 🔄 Borrow (Ödünç Alma)

| HTTP  | Endpoint          | Açıklama                              |
|--------|-------------------|---------------------------------------|
| GET    | /borrows/{id}     | ID’ye göre ödünç kayıt getirir        |
| GET    | /borrows          | Tüm ödünç kayıtlarını listeler        |
| POST   | /borrows          | Kitap ödünç alır                      |
| PUT    | /borrows          | Ödünç kaydını günceller (iade vs.)    |
| DELETE | /borrows/{id}     | Kitap iade eder (silme işlemi)        |

---

## ✅ Validasyon ve Hatalar

- `@Valid` ile request body kontrolleri yapılır.
- Uygun olmayan isteklerde `400 Bad Request`, `404 Not Found`, `409 Conflict` gibi hatalar döner.
- Örnek özel hatalar:
  - `BookAlreadyBorrowedException`
  - `BookUnavailableException`
  - `NotFoundException`

---

## 📦 Kullanılan Teknolojiler

- Java 17+
- Spring Boot
- Spring Data JPA (Hibernate)
- Lombok
- ModelMapper
- PostgreSQL / H2
- RESTful API

---

## Veritabanı Diyagramı
![LibraryDiagram (1)](https://github.com/user-attachments/assets/bf16c379-da72-4edb-9b2f-bfffae378a60)

