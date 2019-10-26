/**
 * 
 */
package com.basic.framework.auth.authorization.userdetails;

import java.util.Collection;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.basic.framework.auth.authorization.model.AuthUserEntity;

/**
 * @author gmc
 *
 */
public class BasicAuthUserDetails implements UserDetails, CredentialsContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final User user;
	
	private AuthUserEntity authUserEntity;

	public BasicAuthUserDetails(User user, AuthUserEntity authUserEntity) {
		this.user = user;
		this.authUserEntity = authUserEntity;
	}
	
	@Override
	public void eraseCredentials() {
		user.eraseCredentials();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getAuthorities();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return user.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return user.isAccountNonExpired();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return user.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}

	public User getUser() {
		return user;
	}

	public AuthUserEntity getAuthUserEntity() {
		return authUserEntity;
	}

}
