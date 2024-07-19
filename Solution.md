# Architecture

For a simple Java application without a UI, with a focus on separation of concerns, 
a combination of Hexagonal Architecture and Clean Architecture would be ideal. 
Each layer has a specific role and does not depend on the details of other layers, 
promoting high cohesion and low coupling while following S.O.L.I.D. principles. 
This approach improves readability, maintainability, and testability by 
making the business rules independent of frameworks and other external elements.

## Layers
### Core (Domain)

Contains the domain entities and use cases, ensuring that the business logic is isolated and independent.

#### Entities

Entities are the core business objects.

#### Use Cases (Interactors)

Use Cases are the application-specific business rules.

### Ports

Interfaces for external systems (e.g., repository interfaces, service interfaces), 
enforcing a clear contract between the core and the external systems.
### Adapters

Actual implementations of the interfaces defined in the ports.
These could include repositories (for data access), external service clients, etc.

### Configuration

The application entry point. Manages dependency injection and application configuration logic.

## Architecture Diagram

The architecture diagram illustrates the layered structure of the application,
highlighting the separation of concerns (layer) and the flow of dependencies.

<img src="bcxp-solution-diagram.png">
