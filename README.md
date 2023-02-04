# Springcloud playground

## Services
- Naming server - 8761
- Config server - 8888
- Api gateway - 8765
- Currency conversion - 8100
- Currency exchange - 8000
## Links
- http://localhost:8765/currency-exchange/from/usd/to/cny - through api
- http://localhost:8765/currency-conversion-feign/from/usd/to/cny/quantity/100 - through api
- http://localhost:8000/currency-exchange/from/usd/to/cny - direct visit
- http://localhost:8761/ - naming server
## Monitoring
- Zipkin: docker run -d -p 9411:9411 openzipkin/zipkin (Blocked by firm laptop)