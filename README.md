# kukulkancraftsman
 
# Instalación local utilizando Yarn (Recomendada)
1. Instalar [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) desde el sitio web de Oracle. 
2. (Opcional) Instalar Maven. El proyecto tiene un Wrapper de Maven que instalar la version adecuada, sin embargo es posible instalarlo desde los paquetes binarios.
3. Instalar [Git](https://git-scm.com/) desde el sition web.
4. Instalar [Node.js](https://nodejs.org/es/) desde el sitio web (utilizar la version LTS)
5. Instalar [Yar](https://yarnpkg.com/en/docs/install) desde el sitio Web
7. Instalar Bower: `yarn global add bower`
8. Instalar Gulp: `yarn global add gulp-cli`

Nota: Si tienes problemas utilizando las herramientas anteriores de manera global, asegurate que tienes la ruta siguiente en tu variable de ambiente `PATH` la siguiente ruta `$HOME/.config/yarn/global/node_modules/.bin`

On Para Mac o Linux: export PATH="$PATH:`yarn global bin`:$HOME/.config/yarn/global/node_modules/.bin"

# Instalar paquetes con Bower

### Instalar un paquete en el archivo bower.json
`$ bower install PACKAGE --save`
### Instalar los paquetes listados en el archivo bower.json
`$ bower install`


# Para inyectar dependencias

Inyectar depedencias front-end en el codigo:
 `gulp inject`

Para generar las constantes AngularJS:
 `gulp ngconstant:dev`

Para hacer los pasos anteriores con un unico comando:
 `gulp install`
 