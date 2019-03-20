# Silver Bars

## Decisions
- Conversion from orders to summary items happens in `Summary`
- No summary builder (liveorders)
- `com.github.dballinger.silverbars.Summary.Summary` iterates over order list twice to separate buy and sell orders. We could alternatively iterate once over the list adding the orders to two mutable lists. This would perform better for large order lists but relying on mutability increases the potential for bugs.