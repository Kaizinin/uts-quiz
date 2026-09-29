# Latihan Spring Boot

Kumpulan dua program sederhana berbasis Spring Boot, masing-masing dengan REST API dan halaman HTML sebagai antarmuka.

| Proyek | Deskripsi | Port |
|---|---|---|
| [`john-travolta`](john-travolta) | Menghitung gaji mingguan beserta lembur, lalu menentukan apakah bisa menabung | 8080 |
| [`persamaan-kuadrat`](persamaan-kuadrat) | Mencari akar persamaan kuadrat `ax² + bx + c = 0` | 8081 |

## Teknologi

- Java 17
- Spring Boot 3.3.4 (Web, Validation)
- Maven
- JUnit 5

## Struktur Folder

```
.
├── README.md
├── .gitignore
├── john-travolta/
│   ├── pom.xml
│   └── src/
│       ├── main/
│       │   ├── java/com/example/travolta/
│       │   │   ├── JohnTravoltaApplication.java
│       │   │   ├── GajiController.java
│       │   │   ├── GajiService.java
│       │   │   ├── GajiRequest.java
│       │   │   └── GajiResponse.java
│       │   └── resources/static/index.html
│       └── test/java/com/example/travolta/GajiServiceTest.java
└── persamaan-kuadrat/
    ├── pom.xml
    └── src/
        ├── main/
        │   ├── java/com/example/kuadrat/
        │   │   ├── PersamaanKuadratApplication.java
        │   │   ├── KuadratController.java
        │   │   ├── KuadratService.java
        │   │   ├── KuadratRequest.java
        │   │   └── KuadratResponse.java
        │   └── resources/
        │       ├── application.properties
        │       └── static/index.html
        └── test/java/com/example/kuadrat/KuadratServiceTest.java
```

## Cara Menjalankan

Prasyarat: JDK 17 atau lebih baru.

Kedua proyek berdiri sendiri, jadi buka salah satu foldernya (`john-travolta` atau `persamaan-kuadrat`) di VS Code atau IntelliJ, lalu jalankan class `*Application.java` dengan tombol Run. Jika Maven terpasang, bisa juga lewat terminal di dalam folder proyek:

```bash
mvn spring-boot:run
```

Setelah aplikasi berjalan, buka halaman utamanya di browser:

- John Travolta: `http://localhost:8080`
- Persamaan Kuadrat: `http://localhost:8081`

Kedua proyek memakai port berbeda, sehingga bisa dijalankan bersamaan.

## Menjalankan Tes

Di dalam folder proyek:

```bash
mvn test
```

Atau jalankan class `*ServiceTest.java` dari IDE.

---

## 1. John Travolta

### Deskripsi Soal

- Gaji normal: 40 jam per minggu dengan rate per jam (bawaan Rp 15.000).
- Lembur (di atas 40 jam) dibayar 1,5 kali rate normal per jam.
- Pemasukan dibandingkan dengan pengeluaran mingguan:
  - pemasukan **>** pengeluaran → `bisa menabung` (beserta jumlah tabungan)
  - pemasukan **=** pengeluaran → `tidak bisa menabung`
  - pemasukan **<** pengeluaran → `cari tambahan`

Jam kerja, rate, dan pengeluaran dapat diisi bebas.

### API

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

### Contoh Kasus

| Jam kerja | Rate | Pengeluaran | Total gaji | Status |
|---|---|---|---|---|
| 52 | 15.000 | 600.000 | 870.000 | bisa menabung (270.000) |
| 40 | 15.000 | 600.000 | 600.000 | tidak bisa menabung |
| 30 | 15.000 | 600.000 | 450.000 | cari tambahan |

---

## 2. Persamaan Kuadrat

### Fitur

Menghitung diskriminan `D = b² - 4ac`, lalu menentukan jenis dan nilai akar:

| Kondisi | Jenis akar |
|---|---|
| D > 0 | Dua akar real berbeda |
| D = 0 | Dua akar real kembar |
| D < 0 | Akar kompleks (`p + qi` dan `p - qi`) |
| a = 0 | Ditolak (bukan persamaan kuadrat) |

### API

`POST /api/kuadrat`

Request:

```json
{ "a": 1, "b": -5, "c": 6 }
```

Response:

```json
{
  "persamaan": "1x² - 5x + 6 = 0",
  "diskriminan": "1",
  "jenisAkar": "Dua akar real berbeda",
  "x1": "3",
  "x2": "2"
}
```

Jika `a = 0`, server membalas status 400 dengan isi `{"message": "..."}`.

### Contoh Kasus

| a | b | c | Hasil |
|---|---|---|---|
| 1 | -5 | 6 | x₁ = 3, x₂ = 2 |
| 1 | -2 | 1 | x₁ = x₂ = 1 |
| 1 | 2 | 5 | x₁ = -1 + 2i, x₂ = -1 - 2i |
| 0 | 2 | 1 | Error: a tidak boleh 0 |

---

## Catatan

Kedua endpoint hanya menerima POST, sehingga membuka `/api/gaji` atau `/api/kuadrat` langsung di browser menghasilkan error 405. Gunakan halaman utama (`/`), Postman, atau `Invoke-RestMethod`.
