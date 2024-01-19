# Reporting Service
The Reporting Service is a microservice in the DTUPay system responsible for generating reports and retrieving payment list from payment service and receiving generation requests.

## Requirements
To run the service locally, ensure that you have the proper dependencies installed
* Maven
* Docker Compose

## Build
To download, build and test the microservice, run the following commands

```Bash
git clone https://github.com/duerko2/reporting-service
```
```Bash
cd reporting-service
```
```Bash
./build.sh
```

## Notes
To run the microservice together with the other services in DTUPay, it is required that you follow the directory structure as followed

```Bash
DTUPay
├── account-service 
├── bank-service
├── payment-service
├── reporting-service
├── token-service
└── system-test
```

Run the following commands to build, test and run all the services and the system tests (assuming you have followed the directory structure and are currently in the DTUPay folder)
```Bash
cd system-test
```

```Bash
./local-build-and-run.sh
```


### Links to the repositories
* [Account Service](https://github.com/duerko2/account-service)
* [Bank Service](https://github.com/duerko2/account-service)
* [Payment Service](https://github.com/duerko2/account-service)
* [Reporting Service](https://github.com/duerko2/account-service)
* [Token Service](https://github.com/duerko2/account-service)
* [System Test](https://github.com/duerko2/account-service)


### Troubleshooting
If you cannot run the build.sh script, ensure that the script is runnable by running the following command (assuming you are in the reporting-service directory)
```Bash
sudo chmod +x build.sh
```
