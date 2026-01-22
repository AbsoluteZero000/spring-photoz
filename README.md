# SpringPhotoz

A Spring Boot photo upload and download service that stores image files in memory with metadata.

This repository contains a complete photo management API built with Spring Boot and Maven. It demonstrates file upload/download, CRUD operations, and includes a simple web interface for uploading photos.

## Features

- **Photo Upload**: Upload image files via multipart form data
- **Photo Download**: Download stored photos with proper content type and filename headers
- **CRUD Operations**: List, get, create, and delete photos
- **Web Interface**: Simple HTML upload form at `/upload.html`
- **In-Memory Storage**: Photos stored as byte arrays with metadata (not persistent across restarts)

## Model

`Photo` entity with fields:
- `id`: String identifier (UUID for new photos)
- `fileName`: String (validated with `@NotEmpty`)
- `contentType`: String (MIME type)
- `data`: byte[] (actual image data, excluded from JSON serialization)

## API Endpoints

### Photo Management
- `GET /` → Returns "Hello, World"
- `GET /photoz` → List all photos (metadata only)
- `GET /photoz/{id}` → Get photo metadata by ID
- `POST /photoz` → Upload photo (multipart form data)
- `DELETE /photoz/{id}` → Delete photo by ID

### File Download
- `GET /download/{id}` → Download photo file with proper headers

### Web Interface
- `GET /upload.html` → Photo upload form

## Requirements

- Java 17+
- Maven 3.6+

## Build & Run

```bash
# Build
./mvnw clean package

# Run
./mvnw spring-boot:run
```

Application starts on port 8080.

## Usage Examples

### Upload via curl
```bash
curl -X POST http://localhost:8080/photoz \
  -F "data=@/path/to/image.jpg"
```

### Download via curl
```bash
curl -O http://localhost:8080/download/{photo-id}
```

### List photos
```bash
curl http://localhost:8080/photoz
```

### Web Upload
Navigate to `http://localhost:8080/upload.html` and use the file upload form.

## Implementation Details

- **Controllers**: `PhotozController` (CRUD), `DownloadController` (file download)
- **Service**: `PhotozService` with in-memory `Map<String, Photo>` storage
- **Validation**: Jakarta Validation for required fields
- **File Handling**: MultipartFile support with byte array storage
- **Error Handling**: Proper 404 responses for missing photos

## Tests

```bash
./mvnw test
```

## Notes

- Data is stored in memory only - lost on application restart
- Default seed includes one photo with id "1" and fileName "hello.jpg"
- No database dependencies - suitable for demos and testing
- Supports any image format via multipart upload