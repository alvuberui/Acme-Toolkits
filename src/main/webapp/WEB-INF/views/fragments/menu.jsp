<%--
- menu.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.components.list" action="/anonymous/artefact/list-all-components"/>
			<acme:menu-suboption code="master.menu.anonymous.chirps.list" action="/any/chirp/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-marpercor8" action="https://github.com/marpercor8"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-ramoonrb" action="https://github.com/ramoonrb"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-marrodgar62" action="https://github.com/marrodgar62"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-cargarrod12" action="https://github.com/cargarrod12"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-alvuberui" action="https://github.com/alvuberui"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link.juagomram4" action="https://github.com/juagomram4"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.components.list" action="/administrator/artefact/list-all-components"/>
			<acme:menu-suboption code="master.menu.administrator.announcements.list" action="/administrator/announcement/list-all-announcements"/>
			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/administrator/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/administrator/populate-sample"/>			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shut-down" action="/administrator/shut-down"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.inventor" access="hasRole('Inventor')">
			<acme:menu-suboption code="master.menu.inventor.components.list" action="/inventor/artefact/list-all-components"/>
			<acme:menu-suboption code="master.menu.inventor.patronages.list" action="/inventor/patronages/list-mine"/>
			<acme:menu-suboption code="master.menu.inventor.announcements.list" action="/inventor/announcement/list-all-announcements"/>
			
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.patron" access="hasRole('Patron')">
			<acme:menu-suboption code="master.menu.patron.components.list" action="/patron/artefact/list-all-components"/>
			<acme:menu-suboption code="master.menu.patron.announcements.list" action="/patron/announcement/list-all-announcements"/>
		
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.any" access="hasRole('Any')">
			<acme:menu-suboption code="master.menu.any.announcements.list" action="/any/announcement/list-all-announcements"/>
			<acme:menu-suboption code="master.menu.any.chirps.list" action="/any/chirp/list"/>
			<acme:menu-suboption code="master.menu.authenticated.system-configuration" action="/authenticated/system-configuration/show"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.provider" access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.components.list" action="/authenticated/artefact/list-all-components"/>
			<acme:menu-suboption code="master.menu.user-account.announcements.list" action="/authenticated/announcement/list-all-announcements"/>
			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-provider" action="/authenticated/provider/create" access="!hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.provider" action="/authenticated/provider/update" access="hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.become-consumer" action="/authenticated/consumer/create" access="!hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.consumer" action="/authenticated/consumer/update" access="hasRole('Consumer')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

