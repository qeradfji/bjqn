   netstat -ano | findstr :8080
taskkill /PID 42212 /F
sudo docker run -d -p 8080:8080 --name test-demo test-demo:v1.0
