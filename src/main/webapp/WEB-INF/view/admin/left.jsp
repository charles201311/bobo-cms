<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Sidebar -->
<style>
a{text-align: center;}
</style>
<ul class="sidebar navbar-nav" >
	<li class="nav-item active" >
	<a class="nav-link" href="/admin">
			<i class="fas fa-fw fa-tachometer-alt"></i> <span>后台首页</span>
	</a></li>
	
	<li class="nav-item"><a class="nav-link"  href="javascript:void(0)" data="/article/selectsByAdmin">
			<i class="fas fa-fw fa-folder"></i> <span>文章管理</span>
	</a></li>

	<li class="nav-item"><a class="nav-link"  href="javascript:void(0)" data="/admin/users">
			<i class="fas fa-fw fa-chart-area"></i> <span>用户管理</span>
	</a></li>
	<li class="nav-item"><a class="nav-link" href="javascript:void(0)" data="待开发">
			<i class="fas fa-fw fa-chart-area"></i> <span>分类管理</span>
	</a></li>
	<li class="nav-item"><a class="nav-link" href="javascript:void(0)" data="待开发">
			<i class="fas fa-fw fa-table"></i> <span>系统设置</span>
	</a></li>
	<li class="nav-item"><a class="nav-link" href="javascript:void(0)" data="/links/selects">
			<i class="fas fa-fw fa-table"></i> <span>友情链接</span>
	</a></li>
</ul>