# Tour Advice App

Tour Advice App(TAA) implements a Rest API to calculate the cost of going on a holiday to all surrounding countries given a budget. It makes use of [geonames](https://www.geonames.org/export/web-services.html#countryInfo) and the [exchangeratesapi](https://exchangeratesapi.io/).

## Usage
To use TAA you:
* Go to **http://localhost:8080/**
* Login to Googles OAuth Service
* Go the following sample url: 
```python
http://localhost:8080/tourAdvice?totalBudget=1200&budgetPerCountry=100&startingCountryCode=BG&currency=EUR
```

* *TotalBudget* - the total budget for the trip
* *BudgetPerCountry* - the budget for each country
* *StartingCountryCode* - the countryCode for the country whose surrounding countries you want to visit.

## Output
When the sample above is used, the result is the following:
```python
numberOfTours	2

leftOverCash	200

countryNeighbourMap	
Greece	
currencyId	"EUR"
currencyAmount	100

Romania	
currencyId	"RON"
currencyAmount	486.622

Turkey	
currencyId	"TRY"
currencyAmount	1821.6387

North Macedonia	
currencyId	"MKD"
currencyAmount	6136.7765

Serbia	
currencyId	"RSD"
currencyAmount	11737.0374

```
