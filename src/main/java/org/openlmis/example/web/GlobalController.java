/*
 * This program is part of the OpenLMIS logistics management information system platform software.
 * Copyright © 2017 VillageReach
 *
 * This program is free software: you can redistribute it and/or modify it under the terms
 * of the GNU Affero General Public License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 *  
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU Affero General Public License for more details. You should have received a copy of
 * the GNU Affero General Public License along with this program. If not, see
 * http://www.gnu.org/licenses.  For additional information contact info@OpenLMIS.org. 
 */

package org.openlmis.example.web;

import org.openlmis.example.exception.ExampleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller that defines exception handler methods that will apply to exceptions raised across the
 * whole application, not in just one module. It is possible to override the global behaviour with
 * exception handler methods in base module controller. So in this example, if a given controller
 * extends BaseController, an exception handler method from BaseController will invoke when
 * ExampleException will be thrown. If a given controller does not extends BaseController,
 * throwing an ExampleException will invoke method from GlobalController.
 */
@ControllerAdvice
public class GlobalController {

  Logger logger = LoggerFactory.getLogger(GlobalController.class);

  @ExceptionHandler(ExampleException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public String handleNotFound(Exception exception) {
    return handleException(exception);
  }

  private String handleException(Exception exception) {
    logger.error(exception.getMessage(), exception);
    return exception.getMessage();
  }

}
