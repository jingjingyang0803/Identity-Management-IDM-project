# IDM - Strategy and Adapter design patterns applied

# DISIGN 1.0

The design 1.0 is based on an inheritance model for the Identity class in the IDM service. The Identity class is the core of the IDM service and contains additional attributes such as UserId, Organization email, Display Name, Provisioned services, Identity status, and Password.

The Identity class is implemented as an abstract class with subclasses for StaffIdentity, StudentIdentity, and VisitorIdentity. Each subclass overrides the methods of the Identity class to provide the specific implementation for generating the attribute values based on organization or identity type.

The StaffIdentity class generates attributes such as email, ID, display name, and provisioned services based on the organization (TAMK or TAU) and identity type (Staff). The StudentIdentity class generates attributes based on the organization (TAMK or TAU) and identity type (Student). The VisitorIdentity class generates attributes based on the organization (Company) and identity type (Visitor).

The SadeIdentity class provides common functionality and attributes for all identity types and is extended by the StaffIdentity, StudentIdentity, and VisitorIdentity classes.

The Main class demonstrates the usage of the Identity classes by creating instances and printing their attributes.

## Dependencies

The design 1.0 implementation uses the `BCrypt` library for hashing passwords.

# DISIGN 2.0

## Overview

The Identity Management (IDM) project aims to provide a flexible and extensible system for managing different types of identities within an organization. The project utilizes the strategy pattern to handle varying attributes and behaviors based on the organization and identity type.

## Features

- Creation and modification of different identity types, including staff, student, and visitor.
- Automatic generation of email addresses, user id, display name, based on the organization(TAMK, TAU or others) and identity type.
- Provisioning of services based on the organization and identity type.
  | | Staff TAMK | Staff TAU | Student TAMK | Student TAU | Visitor |
  | -------------------- | ---------------------- | --------------------- | ------------------ | ----------------- | ------- |
  | | | | | | |
  | Provisioned services | HR, O365, INTRA, PEPPI | HR, O365, INTRA, SISU | O365, INTRA, PAKKI | O365, INTRA, SISU | INTRA |

- Secure storage of passwords using hashing algorithms.

## Class Structure

The IDM project consists of the following classes:

- `SadeIdentity`: An abstract class that serves as the base for different identity types. It provides common attributes and methods such as first name, last name, organization, email, display name, ID, provisioned services, and password handling.
- `StaffIdentity`, `StudentIdentity`, and `VisitorIdentity`: Concrete classes that extend `SadeIdentity` and implement identity-specific attributes and methods.
- `EmailStrategy` and `DisplayStrategy`: Interfaces that define the strategies for generating email addresses and display names.
- `TuniEmailStrategy`, `VisitorEmailStrategy`, `TamkDisplayStrategy`, `TauDisplayStrategy` and `ExtDisplayStrategy`: Concrete classes that implement the email and display strategies for different organizations and identity types.

## Usage

To use the IDM project, follow these steps:

1. Create an instance of the desired identity type, such as `StaffIdentity`, `StudentIdentity`, or `VisitorIdentity`.
2. Set the necessary attributes such as first name, last name, and organization.
3. The `StaffIdentity`, `StudentIdentity`, and `VisitorIdentity` classes allow the user to customize the behavior by directly assigning instances of desired strategies to the `displayStrategy` and `emailStrategy` variables. This allows the user to set the display and email strategies.
   Alternatively, the user can choose to use the default strategies. By default, the `StaffIdentity` and `StudentIdentity` classes use the `TamkDisplayStrategy` (or `TauDisplayStrategy` based on the organization) and `TuniEmailStrategy` classes. On the other hand, the `VisitorIdentity` class uses the `ExtDisplayStrategy` and `VisitorEmailStrategy` classes.
4. Access the generated email, display name, ID, and provisioned services using the respective methods.
5. Set and retrieve passwords securely using the provided password handling methods.

## Example Usage

Here is an example of how to use the IDM project:

```java
// Create a staff identity
StaffIdentity staff = new StaffIdentity("John", "Doe", "TAMK");

// Set email and display strategies
staff.setEmailStrategy(new TuniEmailStrategy());
staff.setDisplayStrategy(new TamkDisplayStrategy());

// Retrieve identity information
String email = staff.getEmail();
String displayName = staff.getDisplayName();
String id = staff.getId();
String[] provisionedServices = staff.getProvisionedServices();

```

## Conclusion

The Identity Management (IDM) project provides a flexible and extensible solution for managing different types of identities within an organization. By utilizing the strategy pattern, the project allows for easy customization and adaptation to varying attribute requirements.

# DISIGN 3.0

## Separate Contacts Service

The Contacts Service is designed to handle contact information for people. Unlike the IDM service, it does not deal with usernames, passwords, or provisioning data to external systems. The Contacts Service can be used by other services, such as a mail list service, to fetch recipients based on specific criteria.

### ContactsService Class

The `ContactsService` class provides methods for managing contact information. It has the following functionalities:

- `addContact(String category, Contact contact)`: Adds a contact to the specified category.
- `getContactsByCategory(String category)`: Retrieves a list of contacts by category.
- `printAllContacts()`: Prints all contacts grouped by category.

## Contact Class

The `Contact` class represents an individual contact and has the following attributes:

- `firstName`: The first name of the contact.
- `lastName`: The last name of the contact.
- `email`: The email address of the contact.
-

### Usage Example

Here is an example of how to use the Contacts Service:

```java
// Create a contacts service instance
ContactsService contactsService = new ContactsService();

// Add contacts to different categories
contactsService.addContact("Friends", new Contact("John", "Doe", "john.doe@example.com"));
contactsService.addContact("Colleagues", new Contact("Jane", "Smith", "jane.smith@example.com"));
contactsService.addContact("Family", new Contact("Mike", "Johnson", "mike.johnson@example.com"));

// Get contacts by category
List<Contact> colleagues = contactsService.getContactsByCategory("Colleagues");

// Print colleagues' contacts
System.out.println("\nList of Colleagues:");
for (Contact colleague : colleagues) {
    System.out.println("Name: " + colleague.getFirstName() + " " + colleague.getLastName());
    System.out.println("Email: " + colleague.getEmail());
    System.out.println();
}

// Print all contacts
contactsService.printAllContacts();

```

### Conclusion

The Contacts Service provides a simple and efficient way to manage contact information. It allows for easy organization of contacts into different categories and retrieval of contacts based on specific categories. This separation from the IDM service ensures a clean and modular design for handling contact-related functionalities.

# Design 4.0 - Adapter Pattern for Connecting IDM Service to Contacts Service

### IdentityAdapter Class

The `IdentityAdapter` class acts as an adapter for the `SadeIdentity` class from the IDM service to be used as a `Contact` in the Contacts service. It implements the `Contact` interface and adapts the methods accordingly.

### ContactsService Class

The `ContactsService` class has been modified to include the following functionalities:

- `addContact(Contact contact)`: Adds a contact to the contacts list.
- `getContacts()`: Retrieves the list of contacts.
- `printAllContacts()`: Prints all contacts.
- `importIdentities(List<SadeIdentity> identities)`: Imports identities from the IDM service and converts them into contacts using the `IdentityAdapter` class.

The `importIdentities` method uses the `IdentityAdapter` class to convert each `SadeIdentity` object into a `Contact` object and adds it to the contacts list.

### Usage Example

Here is an example of how to use the Contacts Service with the IDM service:

```java
// Create a contacts service instance
ContactsService contactsService = new ContactsService();

// Retrieve identities from the IDM service
List<SadeIdentity> identities = idmService.getAllIdentities();

// Import identities as contacts
contactsService.importIdentities(identities);

// Print all contacts
contactsService.printAllContacts();

```

### Conclusion

By using the adapter pattern, the Contacts service can now utilize data from the IDM service by adapting `SadeIdentity` objects into `Contact` objects. This allows for seamless integration between the two services and enables the Contacts service to work with both manually added contacts and imported identities from the IDM service.
