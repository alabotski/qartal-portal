package com.test.portal.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/todo/")
@Slf4j
public class TodoListController {
/*
  private <R extends RequestBean, S extends ResponseBean> SpringExecutionContext<R, S> createExecutionContext(R request,
      String requestUri) {
    RequestContext<R> requestContext = DefaultRequestContext.forRequest(request)
        .requestPath(requestUri)
        .headers(new DefaultMultiMap<>())
        .parameters(new DefaultMultiMap<>())
        .build();
    return new SpringExecutionContext<>(requestContext, new SpringResponseContext<S>());
  }

  @RequestMapping(value = "items", method = RequestMethod.GET)
  public ResponseEntity findAll(HttpServletRequest request) {
    SpringExecutionContext<VoidRequest, LoadItemsResponse> executionContext = createExecutionContext(
        RequestBean.VOID_REQUEST, request.getRequestURI());
    new LoadItemsHandler().handleRequest(executionContext);
    return ResponseEntity.ok(executionContext.getResponseContext()
        .getResponseBean());
  }

  @RequestMapping(value = "add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity addItem(@RequestBody AddItemRequest request, HttpServletRequest httpServletRequest) {
    SpringExecutionContext<AddItemRequest, AddItemResponse> executionContext = createExecutionContext(request,
        httpServletRequest.getRequestURI());
    new AddItemHandler().handleRequest(executionContext);
    return ResponseEntity.ok(executionContext.getResponseContext()
        .getResponseBean());
  }

  @RequestMapping(value = "clear", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity clear(HttpServletRequest httpServletRequest) {
    SpringExecutionContext<VoidRequest, RemoveResponse> executionContext = createExecutionContext(
        RequestBean.VOID_REQUEST, httpServletRequest.getRequestURI());
    new ClearAllHandler().handleRequest(executionContext);
    return ResponseEntity.ok(executionContext.getResponseContext()
        .getResponseBean());
  }

  @RequestMapping(value = "remove-done", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity removeDone(HttpServletRequest httpServletRequest) {
    SpringExecutionContext<VoidRequest, RemoveResponse> executionContext = createExecutionContext(
        RequestBean.VOID_REQUEST, httpServletRequest.getRequestURI());
    new ClearDoneHandler().handleRequest(executionContext);
    return ResponseEntity.ok(executionContext.getResponseContext()
        .getResponseBean());
  }

  @RequestMapping(value = "toggle", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity toggleItem(@RequestBody ToggleItemRequest request, HttpServletRequest httpServletRequest) {
    SpringExecutionContext<ToggleItemRequest, ToggleItemResponse> executionContext = createExecutionContext(request,
        httpServletRequest.getRequestURI());
    new ToggleItemHandler().handleRequest(executionContext);
    return ResponseEntity.ok(executionContext.getResponseContext()
        .getResponseBean());
  }
*/
}
