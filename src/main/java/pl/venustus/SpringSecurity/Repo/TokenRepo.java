/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package pl.venustus.SpringSecurity.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.venustus.SpringSecurity.Model.Token;

public interface TokenRepo extends JpaRepository<Token, Long> {
    Token findByValue(String value);
}
