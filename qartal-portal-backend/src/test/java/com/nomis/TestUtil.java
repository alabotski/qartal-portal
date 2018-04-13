package com.nomis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * TestUtil.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
@Slf4j
public class TestUtil {

  private TestUtil() {
  }

  public static String asJsonString(final Object obj) {
    try {
      final ObjectMapper mapper = new ObjectMapper();
      return mapper.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      log.error("asJsonString", e);
      return StringUtils.EMPTY;
    }
  }
}
