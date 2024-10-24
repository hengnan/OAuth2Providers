# OAuth2Providers
Google Console -> Enable Api and Services -> New Project -> Credentials -> 
Create Credentials -> OAuth Client ID -> Copy Client ID and Secret ID
Do the same for Github

![image](https://github.com/user-attachments/assets/4bd99409-bfa1-472e-91d9-bd72897da428)

Do the same with Github replacing the Authorization callback URL with /login/oauth2/code/github 
Go to application.properties and place in your Client Id and Secret Id.

Accessing localhost:8080 should bring you to a login back that prompts you to login
through Github or Google. When authenticated, you are able to access other endpoints
from this service, for example localhost:8080/welcome. However, this is not possible 
when you are not authenticated/logged out.
