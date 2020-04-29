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
    1. Admin (See access log, edit users, edit locks)
    2. Security (See access log, view users, edit locks)
    3. Secretary (See access log, edit users, view locks)
    4. Supervisor (See access log, view users, view locks)
    5. Worker (Cannot log in)

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
    1. Log in `logIn`
    2. New User `create`
    3. Store new user `store`
    4. Show users `index`
    5. Show specific user `show`
    6. Edit user `edit`
    7. Update user data `update`
    8. Delete user `delete`

## Proxy
1. Authorization management before database access

## Singleton
1. Database connection and query execution (through proxy)
2. Error logging

## Decorator
1. <sub><sup>(Very basic)</sup></sub> query builder

## Factory
1. Seed database from CSV
2. Create debug data for database

## Strategy
1. Models will implement CRUD operations through strategy

# Testing (JUnit)
## Admin Terminal Access
1. Worker cannot login (even if they have email & password)
2. Supervisor:
    1. login
    2. see access log
    3. see user list, but cannot edit it
    4. see lock list, but cannot edit it
3. Secretary:
    1. login
    2. see access log
    3. see user list, and can edit it
    4. see lock list, but cannot edit it
4. Security:
    1. login
    2. see access log
    3. see user list, but cannot edit it
    4. see lock list, and can edit it
5. Admin:
    1. login
    2. see access log
    3. see user list, and can edit it
    4. see lock list, and can edit it
    
## Locks
(everything will be tested as Admin)
1. See Locks list
2. Add new Lock
3. Edit Locks

## Users
(everything will be tested as Admin)
1. See Users list
2. Add new User
3. Edit Users

## Access Control
1. Allowed lock can be added to User
2. Allowed lock can be removed from User
3. Test if a TestUser can enter at an *"allowed"* lock
4. Test if a TestUser can enter at a *"not allowed"* lock
