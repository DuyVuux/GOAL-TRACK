#curl -X 'POST' \
#  'http://localhost:8080/goaltrack/send-email' \
#  -H 'accept: */*' \
#  -H 'Content-Type: application/json' \
#  -d '{
#  "to": "vuducduy1_t67@hus.edu.vn",
#  "subject": "stringgggggggg",
#  "content": "string"
#}'

#curl -X 'POST' \
#  'http://localhost:8080/goaltrack/verify-email?to=vuducduy1_t67%40hus.edu.vn&name=Vu%20Duy' \
#  -H 'accept: */*' \
#  -d ''


curl -X 'POST' \
  'http://localhost:8080/goaltrack/send-email' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "to": "vuducduy1_t67@hus.edu.vn",
  "subject": "Test Email",
  "content": "Hav a nice day Duke"
}'