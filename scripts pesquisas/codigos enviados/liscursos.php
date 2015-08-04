<?php

// Gero a variável com a lista de cursos
// função para lista de cursos por categoria
// 968 é o ID da página que deverá carregar a lista de cursos

$_SESSION['lista_cursos'] = array();

if (get_the_ID() == 968 || get_the_ID() == 2)
	{
	$argscur = array(
		'type' => 'post',
		'child_of' => 10,
		'parent' => '',
		'orderby' => 'name',
		'order' => 'ASC',
		'hide_empty' => 1,
		'hierarchical' => 1,
		'exclude' => '',
		'include' => '',
		'number' => '',
		'taxonomy' => 'category',
		'pad_counts' => false
	);
	$categories = get_categories($argscur);
	$categorias = array();
	foreach($categories as $linhacat)
		{
		$catcursosarg = array(
			'category' => $linhacat->cat_ID,
			'posts_per_page' => 50,
		);
		$catcursos = get_posts($catcursosarg);
		$cursoscateg = array();
		foreach($catcursos as $linhacatcursos)
			{
			$urlpost = get_permalink($linhacatcursos->ID);
			array_push($cursoscateg, array(
				"idpost" => $linhacatcursos->ID,
				"titulopost" => $linhacatcursos->post_title,
				"url" => $urlpost
			));
			}

		$imagem = z_taxonomy_image_url($linhacat->cat_ID);
		$linhacategoria = array(
			"nome" => $linhacat->name,
			"id" => $linhacat->cat_ID,
			"imagem" => $imagem,
			"cursos" => $cursoscateg
		);
		array_push($categorias, $linhacategoria);
		}

	$_SESSION['lista_cursos'] = $categorias;
	}

// termino de gerar a variável
	// Gero a variável com a lista de cursos
// função para lista de cursos por categoria
// 968 é o ID da página que deverá carregar a lista de cursos
