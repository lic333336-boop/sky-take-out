$uri = "http://localhost:8080/admin/common/upload"
$filePath = "C:\Users\41928\.gemini\antigravity\brain\c8afbd25-7ee0-4f56-bcd4-6729ac8fc449\test_upload_image_1765551730500.png"

# Create multipart form data
$boundary = [System.Guid]::NewGuid().ToString()
$LF = "`r`n"

$bodyLines = (
    "--$boundary",
    "Content-Disposition: form-data; name=`"file`"; filename=`"test.png`"",
    "Content-Type: image/png$LF",
    [System.Text.Encoding]::UTF8.GetString([System.IO.File]::ReadAllBytes($filePath)),
    "--$boundary--$LF"
) -join $LF

# Send request
try {
    $response = Invoke-RestMethod -Uri $uri -Method Post -ContentType "multipart/form-data; boundary=$boundary" -Body $bodyLines
    Write-Host "Upload successful!"
    Write-Host "Response: $($response | ConvertTo-Json)"
} catch {
    Write-Host "Error: $_"
    Write-Host "Status Code: $($_.Exception.Response.StatusCode.value__)"
}
