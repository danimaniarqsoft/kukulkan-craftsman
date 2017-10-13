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
 
 
## Template key-params

#### Project
`projectName` : the name of the project

#### Entity
`entity`: Name of the entity
`entityHyphenNotation`: hyphen notation of the entity
`entityHyphenNotationPlural`: hyphen notation in plural format of the entity
`entityCamelCase`: entity in camel-case notation
`entityCamelCasePlural`: entity in camel-case plural format
`hasBlobProperties`: true if the entity has blob properties, false otherwise
`hasTimeProperties`: true if the entity has time properties, false otherwise
`hasLocalDate`: true if the entity has LocalDate properties, false otherwise

#### Entity-Properties
`property.name`: the name of the property in camelCase format
`property.qualifiedName`: the qualifiedName in java world
`property.columnType`: the type of the property in the datamodel representation
`property.blob`: true if the property is blob, false otherwise
`property.time`: true if the property is time, false otherwise
`property.clob`: true if the property is clob, false otherwise
`property.bigDecimal`: true if the property is bigDecimal, false otherwise
`property.localDate`: true if the property is localDate, false otherwise
`property.instant`: true if the property is instant, false otherwise
`property.zoneDateTime`: true if the property is zoneDateTime, false otherwise
 

### template quick reference

`property.columnType?contains("TIMESTAMP")`
`property.columnType?cap_first`



# Running the Atlas project

## Execute Atlas profile for code generation

#### Cloning the master project from github:

	git clone https://github.com/dads-software-brotherhood/kukulkan-craftsman.git

after this your app directory looks must be something like this:

	-[yourAppFolderapp]
		-kukulkan-craftsman

#### change to kukulkan-craftsman folder

	cd kukulkan-craftsman

#### execute the code generator through maven unit test:

	mvn -Dtest=AtlasGenerationTest test

some error message are showed up, but do not worry, everything is ok.

after this you are going to have the next folders:

	-[yourAppFolderapp]
		-kukulkan-craftsman/   -> kukulkan project
		-atlas/                -> atlas code generated


Just copy the files from atlas folder to the main project and that's it!

