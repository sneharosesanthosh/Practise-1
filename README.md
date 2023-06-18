Admin Service
1. save Admin
2. login Admin via username & password
   -> set a session attribute(here: username)

User Service
1. if admin session exists 

    ADMIN OPERATIONS
    -> save
    -> delete a user by Id
    -> update a user by Id
    -> fetch users
    -> fetch a user by Id


Doubts
1. is password required for user entity here?
2. what should be passed to delete, getUser by id ?
    id or username