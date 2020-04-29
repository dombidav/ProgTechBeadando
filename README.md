# Access Control System in Java
A basic managed ACS with MySQL database. User has many (or none) allowed locks, where he/she can enter.
Entering on other locks will be denied by default. I will make a restAPI for lock control if I have time.

# Design Patterns
## MVC Architecture
### Models
- User (__ID: `integer`__, Name: `String`, Email: `String`, Password: `String` Authority: `AuthorityEnum`, Del: `boolean`)
- Lock (__ID: `integer`__, Name: `String`, Del: `boolean`)
- LogEntry (__ID: `integer`__, CreatedAt: `DateTime`, User: `User`, Lock: `Lock`, WasAllowed: `boolean`)

Where:
- AuthorityEnum: 
    0. Admin (See access log, edit users, edit locks)
    0. Security (See access log, view users, edit locks)
    0. Secretary (See access log, edit users, view locks)
    0. Supervisor (See access log, view users, view locks)
    0. Worker (Cannot log in)

### Views
These will be responsible for writing on console
- Log in
- Terminal
- Show dictionary
- Editing
- Add new
- Access a lock (test if user is allowed)

### Controllers
- User controller
    0. Log in `logIn`
    0. New User `create`
    0. Store new user `store`
    0. Show users `index`
    0. Show specific user `show`
    0. Edit user `edit`
    0. Update user data `update`
    0. Delete user `delete`

## Proxy
0. Authorization management before database access

## Singleton
0. Database connection and query execution (through proxy)
0. Error logging

## Decorator
0. <sub><sup>(Very basic)</sup></sub> query builder

## Factory
0. Seed database from CSV
0. Create debug data for database

## Strategy
0. Models will implement CRUD operations through strategy

# Testing (JUnit)
## Admin Terminal Access
0. Worker cannot login (even if they have email & password)
0. Supervisor:
    0. login
    0. see access log
    0. see user list, but cannot edit it
    0. see lock list, but cannot edit it
0. Secretary:
    0. login
    0. see access log
    0. see user list, and can edit it
    0. see lock list, but cannot edit it
0. Security:
    0. login
    0. see access log
    0. see user list, but cannot edit it
    0. see lock list, and can edit it
0. Admin:
    0. login
    0. see access log
    0. see user list, and can edit it
    0. see lock list, and can edit it
    
## Locks
(everything will be tested as Admin)
0. See Locks list
0. Add new Lock
0. Edit Locks

## Users
(everything will be tested as Admin)
0. See Users list
0. Add new User
0. Edit Users

## Access Control
0. Allowed lock can be added to User
0. Allowed lock can be removed from User
0. Test if a TestUser can enter at an *"allowed"* lock
0. Test if a TestUser can enter at a *"not allowed"* lock