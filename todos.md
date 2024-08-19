TODO:
- UserPutController no esta verificando si al crear un usuario el usuario esta registrado,
- esto es importante porque si el usuario esta registrado, solo el usuario registrado podria modificar sus datos
  - no hay test para esto

      - Es necesario que se cambien todos los demas modulos, antes se usaba Username pero deberiamos cambiarlo a UserId   
      - ahora, es necesario recordar que existe UserAuth ...
      - refactorizar los test

      plan:

          day 1:

              - necesito pasar la logica del login al login ✔️
                  moverlo de users to auth ✔️
              - correr test y revisar que todo esta en orden  
                  fixed lo necesario

          day 2:
              - create value objects for user
              - create test for repository revisar que todo esta en orden
                  fixed lo necesario
            
          day 3: fix user services.
              - changes parameters of services from primitives to VO
              - test all services

          day 4:
              - create Command/Query port
              - create Command/Query Bus port
              - create Command/Query Bus adapter
              - create tedt for Command/Query Bus adapter


          day 5: 

              - planing  an estrategic of Command/Query handler 
              - generic test for Command/Query handler 
              - start with the changes in controllers
        
          day 6: 
              - implement Command/Query bus in controllers
              - change controller for use Command/Query
              - test all controllers

          day x
              - update password
              - update username
              - create recovery password
              - create system otp for verify account
              - lack of integration with sonarQube
              - lack of integration and implementation of hateos
              - The logic of retries and deadletters is yet to be created.
                - we need to add monitoring to the application