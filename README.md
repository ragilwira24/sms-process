## SMS Sending Process Sample

Creating a sending sms process using Spring Boot, Thymeleaf, and Twilio SDK to sending the SMS

### Library

- Java
- Spring Boot
- Thymeleaf
- Twilio SDK

To use the Twilio SDK you must create account and fill the properties with yours properties that will be get from Twilio such as,

- <code>twilio.account.sid</code>
- <code>twilio.auth.token</code>
- <code>sms.default.from-number</code>

The sms template is using thymeleaf to binding all the parameter that been declared, and the template are inside the <code>src/main/resources/sms</code>

You can also change the template directory folder by changing in `SMSConfiguration` in `src/main/java`
