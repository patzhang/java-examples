/*
 * Copyright 2017 MD Sayem Ahmed
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codesod.example.validation.rule;

import com.codesod.example.validation.MenuRepository;
import com.codesod.example.validation.OrderDTO.OrderItem;

import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class MenuValidator implements OrderItemValidator {
  private final MenuRepository menuRepository;

  @Override
  public void validate(OrderItem orderItem) {
    String menuId = Optional.ofNullable(orderItem.getMenuId())
        .map(String::trim)
        .filter(id -> !id.isEmpty())
        .orElseThrow(() -> new IllegalArgumentException("A menu item must be specified."));

    if (!menuRepository.menuExists(menuId)) {
      throw new IllegalArgumentException("Given menu [" + menuId + "] does not exist.");
    }
  }
}
