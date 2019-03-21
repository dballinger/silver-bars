# Silver Bars

## Usage

The entrypoint for Silver Bars is 
```Java
com.github.dballinger.silverbars.SilverBars
```
A new instance of this can be created by calling
```Java
com.github.dballinger.silverbars.SilverBars#newInstance
```
### Registering an order
To add an order to the system, call `register()` passing an instance of `Order`. The system will generate and return an identifier of type `OrderId`.

### Cancelling an order
To cancel an order, pass the `OrderId` that was returned when registering the order to `cancel()`.

### Live order summary
To receive a summary of all live orders in the system, call `liveOrders()`. This will return and instance of `Summary`. This object provides two methods: 
- `sell()` will provide a summary of the sell orders
- `buy()` will provide a summary of the buy orders

The summaries will sum the order quantities grouped by the price per unit of the orders.

## Decisions
- Conversion from orders to summary items happens in `Summary`
- No summary builder (liveorders)
- `com.github.dballinger.silverbars.Summary.Summary` iterates over order list twice to separate buy and sell orders. We could alternatively iterate once over the list adding the orders to two mutable lists. This would perform better for large order lists but relying on mutability increases the potential for bugs.
- `OrderType` polymorphism
