/**
 * *****************************************************
 * Copyright (C) 2019 bytedance.com. All Rights Reserved
 * This file is part of bytedance EA project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 *
 * @date 2019/12/18
 * @author wangchunsheng
 * ****************************************************
 **/
package com.wang;

import com.wang.annotation.WangService;
import com.wang.aware.SpringUtils;
import com.wang.config.AppConfig;
import com.wang.domain.Father;
import com.wang.domain.Son;
import com.wang.scaner.CustomScanner;
import com.wang.service.*;
import org.springframework.beans.factory.support.ChildBeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AbstractTypeHierarchyTraversingFilter;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.wang.bean.E;

public class Test {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(AppConfig.class);

		RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
		rootBeanDefinition.getPropertyValues().addPropertyValue("name","john");
		rootBeanDefinition.getPropertyValues().addPropertyValue("age",42);
		rootBeanDefinition.setBeanClass(Father.class);
		ac.registerBeanDefinition("father", rootBeanDefinition);
		GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
		genericBeanDefinition.setBeanClass(Son.class);
		genericBeanDefinition.getPropertyValues().addPropertyValue("age",12);
		genericBeanDefinition.setParentName("father");
		ac.registerBeanDefinition("son", genericBeanDefinition);


//		System.out.println(ac.getBean(UserService.class).getIndexService().getUserService());
//		ac.scan();

//		CustomScanner customScanner = new CustomScanner(ac);
//		customScanner.addIncludeFilter(new AnnotationTypeFilter(WangService.class));
//		int scan = customScanner.scan("com.wang");
//		System.out.println(scan);

//		System.out.println(ac.getBean(IndexService.class));

//		RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
//		rootBeanDefinition.getPropertyValues().add("name","wang");
//		rootBeanDefinition.setBeanClass(IndexService.class);
//		ac.registerBeanDefinition("indexService",rootBeanDefinition);
//
//
//		ChildBeanDefinition childBeanDefinition = new ChildBeanDefinition("indexService");
//		rootBeanDefinition.getPropertyValues().add("name","wang");
//		rootBeanDefinition.setBeanClass(IndexService.class);
//
//		/**
//		 * 扫描 类
//		 * 类-bd  BeanDefinition
//		 * bd-bean
//		 */
//		ac.refresh();
//		System.out.println(ac.getBean(IndexService.class).getName());

//		RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
//		rootBeanDefinition.setBeanClass(MyService.class);
//		rootBeanDefinition.getPropertyValues().add("name","wang");
//		ac.registerBeanDefinition("myService",rootBeanDefinition);
//		ChildBeanDefinition childBeanDefinition = new ChildBeanDefinition("myService");
//		childBeanDefinition.setBeanClass(MyService1.class);
//		ac.registerBeanDefinition("myService1",childBeanDefinition);
		ac.refresh();
		Father father = (Father) ac.getBean("father");
		System.out.println(father.getName());
		System.out.println(ac.getBean(Son.class).getName());
//		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
//		System.out.println(ac.getBean(AppConfig.class).getClass());
//		System.out.println(Arrays.toString(beanDefinitionNames));
//		System.out.println(ac.getBean(MyService1.class).getName());
//		CustomScanner customScanner = new CustomScanner(ac);
//		customScanner.addIncludeFilter((metadataReader, metadataReaderFactory) -> true);
//		customScanner.addIncludeFilter(new AbstractTypeHierarchyTraversingFilter(true,true) {
//			@Override
//			public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
//				return true;
//			}
//		});
//		int scan = customScanner.scan("com.wang.inter");
//		System.out.println(scan);
//		WangAspectService wangAspectService = ac.getBean(WangAspectService.class);
//		wangAspectService.test();
//		wangAspectService.test1();
//		System.out.println(ac.getBean(E.class));
//		System.out.println(ac.getBean(SpringUtils.class).getApplicationContext());
//		System.out.println(ac.getBean(MyService.class));
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(IndexService.class);
			System.out.println(Arrays.stream(beanInfo.getPropertyDescriptors()).filter(it -> it.getWriteMethod() != null).map(PropertyDescriptor::getName).collect(Collectors.joining(",")));
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
	}

}
