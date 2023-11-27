SELECT * FROM TASKFLOW.USERS 


EXEC taskFlow.spAddUser
    @first_name = "Rafael",
    @last_name = "Moreira",
    @username = "admin",
    @email = "admin@gmail.com",
    @password_hash = "admin",
    @profile_image = null
