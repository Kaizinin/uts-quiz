# Soal John Travolta (Spring Boot)

Aplikasi web sederhana berbasis Spring Boot untuk menyelesaikan soal John Travolta: menghitung gaji mingguan (termasuk lembur) dan menentukan apakah ia bisa menabung.

## Deskripsi Soal

- Gaji normal: 40 jam per minggu dengan rate per jam (bawaan Rp 15.000).
- Lembur (di atas 40 jam) dibayar 1,5 kali rate normal per jam.
- Pemasukan dibandingkan dengan pengeluaran mingguan:
  - pemasukan **>** pengeluaran → `bisa menabung` (beserta jumlah tabungan)
  - pemasukan **=** pengeluaran → `tidak bisa menabung`
  - pemasukan **<** pengeluaran → `cari tambahan`

Jam kerja, rate, dan pengeluaran dapat diisi bebas.

## Teknologi

- Java 17
- Spring Boot 3.3.4 (Web, Validation)
- Maven
- JUnit 5 untuk tes unit

## Struktur Proyek

```
john-travolta/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/com/example/travolta/
    │   │   ├── JohnTravoltaApplication.java
    │   │   ├── GajiController.java
    │   │   ├── GajiService.java
    │   │   ├── GajiRequest.java
    │   │   └── GajiResponse.java
    │   └── resources/static/index.html
    └── test/java/com/example/travolta/
        └── GajiServiceTest.java
```

## Cara Menjalankan

Prasyarat: JDK 17 atau lebih baru.

**Lewat VS Code / IntelliJ:** buka folder proyek, lalu jalankan `JohnTravoltaApplication.java` (tombol Run).

**Lewat terminal (jika Maven terpasang):**

```bash
mvn spring-boot:run
```

Buka `http://localhost:8080` di browser, isi form, lalu klik **Hitung**.

## Menjalankan Tes

```bash
mvn test
```

Atau jalankan `GajiServiceTest.java` dari IDE. Ada 3 tes yang mencakup ketiga kondisi status.

## API

`POST /api/gaji`

Request:

```json
{ "jamKerja": 52, "rate": 15000, "pengeluaran": 600000 }
```

Response:

```json
{
  "jamNormal": 40,
  "jamLembur": 12,
  "gajiNormal": 600000,
  "gajiLembur": 270000.0,
  "totalGaji": 870000.0,
  "pengeluaran": 600000,
  "status": "bisa menabung",
  "tabungan": 270000.0
}
```

Catatan: endpoint hanya menerima POST, sehingga membuka `/api/gaji` langsung di browser menghasilkan error 405. Gunakan halaman utama (`/`), Postman, atau `Invoke-RestMethod`.

## Contoh Kasus

| Jam kerja | Rate | Pengeluaran | Total gaji | Status |
|---|---|---|---|---|
| 52 | 15.000 | 600.000 | 870.000 | bisa menabung (270.000) |
| 40 | 15.000 | 600.000 | 600.000 | tidak bisa menabung |
| 30 | 15.000 | 600.000 | 450.000 | cari tambahan |
