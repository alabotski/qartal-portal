package com.nomis.client.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.nomis.client.application.ApplicationModule;
import com.nomis.client.application.error.ErrorModule;
import com.nomis.client.application.home.HomeModule;
import com.nomis.client.application.login.LoginModule;
import com.nomis.client.css.ResourceLoader;
import com.nomis.client.model.Person;
import com.nomis.client.place.NameTokens;
import com.nomis.client.widget.loading.LoadingModule;

/**
 * ClientModule.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
//@GwtpApp
public class ClientModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    //Defaults.setServiceRoot("");

    install(new DefaultModule.Builder()
        .defaultPlace(NameTokens.home)
        .errorPlace(NameTokens.error)
        .unauthorizedPlace(NameTokens.login)
        .build());

    install(new ApplicationModule());
    install(new LoginModule());
    install(new HomeModule());
    install(new ErrorModule());
    install(new LoadingModule());

    bind(ResourceLoader.class).asEagerSingleton();
    bind(Person.class).asEagerSingleton();
  }
}
