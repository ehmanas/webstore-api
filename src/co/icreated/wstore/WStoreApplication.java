/*******************************************************************************
 * @author Copyright (C) 2019 ICreated, Sergey Polyarus
 *  @date 2019
 *  This program is free software; you can redistribute it and/or modify it
 *  under the terms version 2 of the GNU General Public License as published
 *  by the Free Software Foundation. This program is distributed in the hope
 *  that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 *  warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *  See the GNU General Public License for more details.
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc., 
 *  59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
 ******************************************************************************/
package co.icreated.wstore;

import java.util.Properties;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.message.GZipEncoder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.EncodingFilter;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import co.icreated.wstore.endpoints.AccountEndpoints;
import co.icreated.wstore.endpoints.AuthenticationEndpoints;
import co.icreated.wstore.endpoints.CatalogEndpoints;
import co.icreated.wstore.endpoints.CheckoutEndpoints;
import co.icreated.wstore.endpoints.CommonEndpoints;
import co.icreated.wstore.factory.AccountServiceFactory;
import co.icreated.wstore.factory.CatalogServiceFactory;
import co.icreated.wstore.factory.ContextFactory;
import co.icreated.wstore.factory.OrderServiceFactory;
import co.icreated.wstore.factory.PaymentServiceFactory;
import co.icreated.wstore.security.CORSFilter;
import co.icreated.wstore.security.CheckRequestFilter;
import co.icreated.wstore.security.JwtAuthenticationFilter;
import co.icreated.wstore.service.AccountService;
import co.icreated.wstore.service.CatalogService;
import co.icreated.wstore.service.OrderService;
import co.icreated.wstore.service.PaymentService;

public class WStoreApplication extends ResourceConfig {
	
    public WStoreApplication() {
       
 //   	register(RolesAllowedDynamicFeature.class);
    	register(AccountEndpoints.class);
    	register(CatalogEndpoints.class);
    	
    	register(CommonEndpoints.class);
    	register(CheckoutEndpoints.class);
    	
    	register(CORSFilter.class);
    	register(CheckRequestFilter.class);
    	register(JwtAuthenticationFilter.class);
    	register(AuthenticationEndpoints.class);

 //   	register(new LoggingFilter());
    	
//		register(GZipEncoder.class);
		EncodingFilter.enableFor(this, GZipEncoder.class);
    	
    	register(new JacksonJsonProvider());
    	
    	
    	register(new AbstractBinder(){
            @Override
            protected void configure() {
                bindFactory(ContextFactory.class)
                        .to(Properties.class);
            } 
        });
    	
    	register(new AbstractBinder(){
            @Override
            protected void configure() {
                bindFactory(CatalogServiceFactory.class)
                        .to(CatalogService.class);
            } 
        });
    	
    	register(new AbstractBinder(){
            @Override
            protected void configure() {
                bindFactory(AccountServiceFactory.class)
                        .to(AccountService.class);
            } 
        });
    	
    	register(new AbstractBinder(){
            @Override
            protected void configure() {
                bindFactory(OrderServiceFactory.class)
                        .to(OrderService.class);
            } 
        });
    	
    	register(new AbstractBinder(){
            @Override
            protected void configure() {
                bindFactory(PaymentServiceFactory.class)
                        .to(PaymentService.class);
            } 
        });

    	
    }

}
