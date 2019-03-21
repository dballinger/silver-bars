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
To receive a summary of all live orders in the system, call `liveOrders()`. This will return an instance of `Summary`. This object provides two methods: 
- `sell()` will provide a summary of the sell orders
- `buy()` will provide a summary of the buy orders

The summaries will sum the order quantities grouped by the price per unit of the orders.

## Decisions
- I originally had a builder for `Summary` that was injected into `SilverBars` that decoupled `SilverBars` from `Summary`. This dependency inversion just didn't seem to offer any benefits in this current situation so I changed it so that `Summary` is instantiated directly from `SilverBars`. Problems relating to this would show up by tests becoming hard to write so this may be revisited later after listening to tests.
- `com.github.dballinger.silverbars.Summary.Summary` iterates over the order list twice to separate buy and sell orders. We could alternatively iterate once over the list adding the orders to two mutable lists. This would perform better for large order lists but relying on mutability increases the potential for bugs.
- There is a need for polymorphism somewhere in the system to differentiate buy/sell behaviours. I decided to only model this in the summarisation components as the spec indicated that this was the only area where differences occur (sorting). However, in the real world, I would imagine that there would be some other differences with the order flow. In this case, it may be better to move the buy/sell polymorphism earlier in the chain.
- I introduced types to model the quantity and price. This allows for extension in the future when we may be dealing with units other than KG and GBP. However, I didn't go as far as to create a type hierarchy for these as I thought this would add unnecessary noise at this stage. 
- The primitive type inside `GBP` is `int`. This may want to be a `BigDecimal` but I couldn't see the need for this just yet, based on the spec and example.

__I'd really welcome feedback on these decisions.__
