<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Sidebar -->
<style>
a{text-align: center;}
</style>
<ul class="sidebar navbar-nav bg-dark" >
	<li class="nav-item "  >
	<a class="nav-link list-group-item-info" href="/admin">
			<i class="fas fa-fw fa-tachometer-alt"></i> <span>后台首页</span>
	</a></li>
	
	<li class="nav-item"><a class="nav-link"  href="javascript:void(0)" data="/admin/articles">
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
		<li class="nav-item"><a class="nav-link" href="javascript:void(0)" data="/admin/special/selects">
			<i class="fas fa-fw fa-table"></i> <span>文章专题</span>
	</a></li>
</ul>