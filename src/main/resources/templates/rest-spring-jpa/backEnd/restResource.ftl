<#assign aDateTime = .now>
/*
 *  
 * The MIT License (MIT)
 * Copyright (c) ${year} ${author}
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
${package}

import java.util.List;
import java.util.Optional;

import com.codahale.metrics.annotation.Timed;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.web.util.UriComponentsBuilder;
import mx.infotec.dads.rsr.web.rest.util.*;

${importModel}
<#if importPrimaryKey??>
${importPrimaryKey}
</#if>
${importService}
/**
 * 
 * @author ${author}
 * @kukulkanGenerated ${aDateTime?iso_utc}
 */

@RestController
@RequestMapping("/api")
public class ${name}RestController {

    private static final Logger log = LoggerFactory.getLogger(${name}RestController.class);
    
    private static final String ENTITY_NAME = "${propertyName}";

    @Autowired
    private ${name}Service service;
    
    /**
     * GET  /${propertyNamePlural} : recupera todos los ${propertyNamePlural}.
     *
     * @param pageable información de paginación
     * @return El objeto ResponseEntity con estado de 200 (OK) y la lista de ${propertyNamePlural} en el cuerpo del mensaje
     */
    @GetMapping("/${propertyNamePlural}")
    @Timed
    public ResponseEntity<List<${name}>> getAll${name}(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of ${name}");
        Page<${name}> page = service.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/${propertyNamePlural}");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /${propertyNamePlural}/:id : recupera por "id" de ${name}.
     *
     * @param id el id del ${name} que se desea recuperar
     * @return El objeto ResponseEntity con el estado de 200 (OK) y dentro del cuerpo del mensaje el ${name}, o con estado de 404 (Not Found)
     */
    @GetMapping("/${propertyNamePlural}/{id}")
    @Timed
    public ResponseEntity<${name}> get${name}(@PathVariable ${id} id) {
        log.debug("REST request to get ${name} : {}", id);
        ${name} ${propertyName} = service.findById(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(${propertyName}));
    }

    /**
     * POST  /${propertyNamePlural} : Create a new usuario.
     *
     * @param ${propertyName} el ${propertyName} que se desea crear
     * @return El objeto ResponseEntity con estado 201 (Created) y en el cuerpo un nuevo ${propertyName}, o con estado 400 (Bad Request) si el usuario ya tiene un ID
     * @throws URISyntaxException Si la sintaxis de la URI no es correcta
     */
    @PostMapping("/${propertyNamePlural}")
    @Timed
    public ResponseEntity<${name}> create${name}(@Valid @RequestBody ${name} ${propertyName}) throws URISyntaxException {
        log.debug("REST request to save ${name} : {}", ${propertyName});
        if (${propertyName}.get${primaryKey.name?cap_first}() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new ${propertyName} cannot already have an ID")).body(null);
        }
        ${name} result = service.save(${propertyName});
        return ResponseEntity.created(new URI("/api/${propertyNamePlural}/" + result.get${primaryKey.name?cap_first}()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.get${primaryKey.name?cap_first}().toString()))
            .body(result);
    }
    
    /**
     * PUT  /${propertyNamePlural} : Actualiza un ${name} existente.
     *
     * @param ${propertyName} el ${propertyName} que se desea actualizar
     * @return el objeto ResponseEntity con estado de 200 (OK) y en el cuerpo de la respuesta el ${name} actualizado,
     * o con estatus de 400 (Bad Request) si el ${propertyName} no es valido,
     * o con estatus de 500 (Internal Server Error) si el ${propertyName} no se puede actualizar
     * @throws URISyntaxException si la sintaxis de la URI no es correcta
     */
    @PutMapping("/${propertyNamePlural}")
    @Timed
    public ResponseEntity<${name}> update${name}(@Valid @RequestBody ${name} ${propertyName}) throws URISyntaxException {
        log.debug("REST request to update ${name} : {}", ${propertyName});
        if (${propertyName}.get${primaryKey.name?cap_first}() == null) {
            return create${name}(${propertyName});
        }
        ${name} result = service.save(${propertyName});
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ${propertyName}.get${primaryKey.name?cap_first}().toString()))
            .body(result);
    }


    /**
     * DELETE  /${propertyNamePlural}/:id : borrar el ${name} con "id".
     *
     * @param id el id del ${name} que se desea borrar
     * @return el objeto ResponseEntity con estatus 200 (OK)
     */
    @DeleteMapping("/${propertyNamePlural}/{id}")
    @Timed
    public ResponseEntity<Void> delete${name}(@PathVariable ${id} id) {
        log.debug("REST request to delete ${name} : {}", id);
        service.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/${propertyNamePlural}?query=:query : buscar por el ${propertyName} correspondiente
     * to the query.
     *
     * @param query el query para el ${propertyName} que se desea buscar
     * @param pageable información de la paginación
     * @return el resultado de la busqueda
     */
    @GetMapping("/_search/${propertyNamePlural}")
    @Timed
    public ResponseEntity<List<${name}>> search${name}(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of ${name} for query {}", query);
        Page<${name}> page = service.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/${propertyNamePlural}");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
}