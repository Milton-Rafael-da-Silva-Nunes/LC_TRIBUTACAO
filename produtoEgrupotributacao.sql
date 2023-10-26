-- MySQL dump 10.13  Distrib 5.5.38, for Win64 (x86)
--
-- Host: localhost    Database: lc_sistemas
-- ------------------------------------------------------
-- Server version	5.5.38

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(50) DEFAULT NULL,
  `referencia` varchar(50) DEFAULT NULL,
  `codigo_barras` varchar(15) DEFAULT NULL,
  `nome` varchar(250) DEFAULT NULL,
  `descricao` varchar(250) DEFAULT NULL,
  `id_grupotributacao` int(11) DEFAULT NULL,
  `id_categoria` int(11) DEFAULT NULL,
  `id_cfop` int(11) DEFAULT NULL,
  `id_cst` int(11) DEFAULT NULL,
  `id_ncm` int(11) DEFAULT NULL,
  `id_cest` int(11) DEFAULT NULL,
  `id_fabricante` int(11) DEFAULT NULL,
  `id_fornecedor` int(11) DEFAULT NULL,
  `id_unidade` int(11) DEFAULT NULL,
  `id_unidadeatacado2` int(11) DEFAULT NULL,
  `id_unidadeatacado3` int(11) DEFAULT NULL,
  `id_unidadeatacado4` int(11) DEFAULT NULL,
  `id_unidadeembalagem` int(11) DEFAULT NULL,
  `id_subcategoria` int(11) DEFAULT NULL,
  `id_empresa` int(11) NOT NULL,
  `pode_desconto` varchar(1) DEFAULT NULL,
  `pode_fracionado` varchar(1) DEFAULT NULL,
  `pode_balanca` varchar(1) DEFAULT NULL,
  `pode_lote` varchar(1) DEFAULT NULL,
  `pode_comissao` varchar(1) DEFAULT NULL,
  `pode_lerpeso` varchar(1) DEFAULT NULL,
  `pode_atualizarncm` varchar(1) DEFAULT NULL,
  `datahora_cadastro` datetime DEFAULT NULL,
  `datahora_alteracao` datetime DEFAULT NULL,
  `preco_compra` double(12,3) DEFAULT NULL,
  `valor_compra` double(12,3) DEFAULT NULL,
  `preco_custo` double(12,3) DEFAULT NULL,
  `custo_medio` double(12,3) DEFAULT NULL,
  `preco_venda` double(12,3) DEFAULT NULL,
  `margem_ideal` double(12,3) DEFAULT NULL,
  `margem_lucro` double(12,3) DEFAULT NULL,
  `desconto_max` double(12,3) DEFAULT NULL,
  `preco_venda2` double(12,3) DEFAULT NULL,
  `margem_lucro2` double(12,3) DEFAULT NULL,
  `qtd_minimapv2` double(12,3) DEFAULT NULL,
  `desconto_max2` double(12,3) DEFAULT NULL,
  `preco_venda3` double(12,3) DEFAULT NULL,
  `margem_lucro3` double(12,3) DEFAULT NULL,
  `qtd_minimapv3` double(12,3) DEFAULT NULL,
  `desconto_max3` double(12,3) DEFAULT NULL,
  `preco_venda4` double(12,3) DEFAULT NULL,
  `margem_lucro4` double(12,3) DEFAULT NULL,
  `qtd_minimapv4` double(12,3) DEFAULT NULL,
  `desconto_max4` double(12,3) DEFAULT NULL,
  `preco_antigo` double(12,3) DEFAULT NULL,
  `valor_frete` double(12,3) DEFAULT NULL,
  `ipi` double(12,3) DEFAULT NULL,
  `preco_promocao` double(12,3) DEFAULT NULL,
  `data_promocaoinicial` date DEFAULT NULL,
  `data_promocaofinal` date DEFAULT NULL,
  `comissao` double(12,3) DEFAULT NULL,
  `comissao_valor` double(12,3) DEFAULT NULL,
  `fidelidade_pontos` double(12,3) DEFAULT NULL,
  `estoque` double(12,3) DEFAULT NULL,
  `estoque_minimo` double(12,3) DEFAULT NULL,
  `estoque_max` double(12,3) DEFAULT NULL,
  `estoque_tara` double(12,3) DEFAULT NULL,
  `qtd_embalagem` double(12,3) DEFAULT NULL,
  `qtd_diasvalidade` varchar(4) DEFAULT NULL,
  `peso_bruto` double(12,3) DEFAULT NULL,
  `peso_liquido` double(12,3) DEFAULT NULL,
  `tipo_produto` varchar(10) DEFAULT NULL,
  `origem_produto` varchar(1) DEFAULT NULL,
  `ex_tipi` varchar(3) DEFAULT NULL,
  `ativo` varchar(1) DEFAULT NULL,
  `observacoes` longtext,
  `foto` varchar(120) DEFAULT NULL,
  `foto2` varchar(120) DEFAULT NULL,
  `foto3` varchar(120) DEFAULT NULL,
  `local` varchar(80) DEFAULT NULL,
  `ref_cruzada1` varchar(50) DEFAULT NULL,
  `ref_cruzada2` varchar(50) DEFAULT NULL,
  `ref_cruzada3` varchar(50) DEFAULT NULL,
  `ref_cruzada4` varchar(50) DEFAULT NULL,
  `ref_cruzada5` varchar(50) DEFAULT NULL,
  `ref_cruzada6` varchar(50) DEFAULT NULL,
  `cod_ean` varchar(14) DEFAULT NULL,
  `codigo_med` varchar(13) DEFAULT NULL,
  `tipo_med` varchar(12) DEFAULT NULL,
  `tabela_med` varchar(12) DEFAULT NULL,
  `linha_med` varchar(30) DEFAULT NULL,
  `ref_anvisa_med` varchar(50) DEFAULT NULL,
  `portaria_med` varchar(15) DEFAULT '',
  `rms_med` varchar(17) DEFAULT NULL,
  `data_vigencia_med` date DEFAULT NULL,
  `edicao_pharmacos` varchar(6) DEFAULT NULL,
  `comb_cprodanp` varchar(10) DEFAULT NULL,
  `comb_descanp` varchar(100) DEFAULT NULL,
  `comb_percentualgaspetroleo` double(12,3) DEFAULT NULL,
  `comb_percentualgasnaturalnacional` double(12,3) DEFAULT NULL,
  `comb_percentualgasnaturalimportado` double(12,3) DEFAULT NULL,
  `comb_valorpartida` double(12,3) DEFAULT NULL,
  `med_classeterapeutica` varchar(18) DEFAULT NULL,
  `med_unidademedida` varchar(7) DEFAULT NULL,
  `med_usoprolongado` varchar(3) DEFAULT NULL,
  `med_podeatualizar` varchar(1) DEFAULT NULL,
  `med_precovendafpop` double(12,3) DEFAULT NULL,
  `med_margemfpop` double(12,3) DEFAULT NULL,
  `med_apresentacaofpop` double(12,3) DEFAULT NULL,
  `trib_issaliqsaida` double(12,3) DEFAULT NULL,
  `trib_icmsaliqsaida` double(12,3) DEFAULT NULL,
  `trib_icmsaliqredbasecalcsaida` double(12,3) DEFAULT NULL,
  `trib_icmsobs` varchar(80) DEFAULT NULL,
  `trib_icmsfcpaliq` double(12,3) DEFAULT NULL,
  `trib_ipisaida` varchar(2) DEFAULT NULL,
  `trib_ipialiqsaida` double(12,3) DEFAULT NULL,
  `trib_pissaida` varchar(2) DEFAULT NULL,
  `trib_pisaliqsaida` double(12,3) DEFAULT NULL,
  `trib_cofinssaida` varchar(2) DEFAULT NULL,
  `trib_cofinsaliqsaida` double(12,3) DEFAULT NULL,
  `trib_genero` varchar(3) DEFAULT NULL,
  `imendes_codigointerno` varchar(20) DEFAULT NULL,
  `imendes_produtonome` varchar(120) DEFAULT NULL,
  `imendes_datahoraalteracacao` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupotributacao`
--

DROP TABLE IF EXISTS `grupotributacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupotributacao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(120) DEFAULT NULL,
  `uf` varchar(2) DEFAULT NULL,
  `id_estado` int(11) DEFAULT NULL,
  `id_ncm` int(11) DEFAULT NULL,
  `id_cest` int(11) DEFAULT NULL,
  `id_cst` int(11) DEFAULT NULL,
  `id_cfop` int(11) DEFAULT NULL,
  `id_cfop_bonificacao` int(11) DEFAULT NULL,
  `id_cfop_devolucao` int(11) DEFAULT NULL,
  `id_cfop_transferencia` int(11) DEFAULT NULL,
  `ncm` varchar(10) DEFAULT NULL,
  `origem` varchar(4) DEFAULT NULL,
  `genero` varchar(4) DEFAULT NULL,
  `icms_saida_aliquota` double(12,3) DEFAULT NULL,
  `icms_saida_aliquota_red_base_calc` double(12,3) DEFAULT NULL,
  `icms_fcp_aliquota` double(12,3) DEFAULT NULL,
  `icms_observacao_fiscal` varchar(50) DEFAULT NULL,
  `icms_difererimento_aliquota` double(12,3) DEFAULT NULL,
  `icms_desonerado_aliquota` double(12,3) DEFAULT NULL,
  `icms_st_aliquota` double(12,3) DEFAULT NULL,
  `icms_st_red_base_calc_aliquota` double(12,3) DEFAULT NULL,
  `icms_isencao_aliquota` double(12,3) DEFAULT NULL,
  `icms_iva` double(12,3) DEFAULT NULL,
  `icms_codigo_beneficio_fiscal` varchar(10) DEFAULT NULL,
  `pis_saida` varchar(2) DEFAULT NULL,
  `pis_saida_aliquota` double(12,3) DEFAULT NULL,
  `pis_nri` varchar(5) DEFAULT NULL,
  `cofins_saida` varchar(2) DEFAULT NULL,
  `cofins_saida_aliquota` double(12,3) DEFAULT NULL,
  `cofins_nri` varchar(3) DEFAULT NULL,
  `ipi_cst` varchar(2) DEFAULT NULL,
  `ipi_ex` varchar(2) DEFAULT NULL,
  `ipi_aliquota` double(12,3) DEFAULT NULL,
  `ipi_codigo_enquadramento` varchar(5) DEFAULT NULL,
  `preco_cmv` double(12,3) DEFAULT NULL,
  `imendes_codigo_grupo` varchar(20) DEFAULT NULL,
  `imendes_codigo_regra` varchar(20) DEFAULT NULL,
  `imendes_datahora_alteracao` datetime DEFAULT NULL,
  `datahora_alteracao` datetime DEFAULT NULL,
  `ativo` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupotributacao`
--

LOCK TABLES `grupotributacao` WRITE;
/*!40000 ALTER TABLE `grupotributacao` DISABLE KEYS */;
INSERT INTO `grupotributacao` VALUES (1,'GRUPO PADRAO','PA',14,1,NULL,14,289,414,23,311,'00000000','0','',0.000,0.000,0.000,'',NULL,NULL,NULL,NULL,NULL,NULL,'','07',0.000,NULL,'07',0.000,NULL,'53','',0.000,'999',0.000,NULL,NULL,NULL,'2023-07-06 09:38:33','1'),(2,'| CST 400  /  CFOP 5102  /  PIS e COFINS 07 * 07  /  ORIGEM 2','PA',14,1,0,20,289,414,23,311,'00000000','2','',0.000,0.000,0.000,'CRIADO POR AJUSTE xls',0.000,0.000,0.000,0.000,0.000,0.000,'','07',0.000,'','07',0.000,'','','',0.000,'',0.000,'','',NULL,'2023-10-26 11:00:03','1'),(3,'| CST 400  /  CFOP 5102  /  PIS e COFINS 07 * 07  /  ORIGEM 0','PA',14,1,0,20,289,414,23,311,'00000000','0','',0.000,0.000,0.000,'CRIADO POR AJUSTE xls',0.000,0.000,0.000,0.000,0.000,0.000,'','07',0.000,'','07',0.000,'','','',0.000,'',0.000,'','',NULL,'2023-10-26 11:00:03','1'),(4,'| CST 500  /  CFOP 5405  /  PIS e COFINS 49 * 49  /  ORIGEM 0','PA',14,1,0,21,355,414,23,311,'00000000','0','',0.000,0.000,0.000,'CRIADO POR AJUSTE xls',0.000,0.000,0.000,0.000,0.000,0.000,'','49',0.000,'','49',0.000,'','','',0.000,'',0.000,'','',NULL,'2023-10-26 11:00:03','1'),(5,'| CST 102  /  CFOP 5102  /  PIS e COFINS 49 * 49  /  ORIGEM 0','PA',14,1,0,14,289,414,23,311,'00000000','0','',0.000,0.000,0.000,'CRIADO POR AJUSTE xls',0.000,0.000,0.000,0.000,0.000,0.000,'','49',0.000,'','49',0.000,'','','',0.000,'',0.000,'','',NULL,'2023-10-26 11:00:03','1'),(6,'| CST 400  /  CFOP 5102  /  PIS e COFINS 07 * 07  /  ORIGEM 2','PA',14,1,0,20,289,414,23,311,'00000000','2','',0.000,0.000,0.000,'CRIADO POR AJUSTE xls',0.000,0.000,0.000,0.000,0.000,0.000,'','07',0.000,'','07',0.000,'','','',0.000,'',0.000,'','',NULL,'2023-10-26 11:08:56','1'),(7,'| CST 400  /  CFOP 5102  /  PIS e COFINS 07 * 07  /  ORIGEM 0','PA',14,1,0,20,289,414,23,311,'00000000','0','',0.000,0.000,0.000,'CRIADO POR AJUSTE xls',0.000,0.000,0.000,0.000,0.000,0.000,'','07',0.000,'','07',0.000,'','','',0.000,'',0.000,'','',NULL,'2023-10-26 11:08:56','1'),(8,'| CST 500  /  CFOP 5405  /  PIS e COFINS 49 * 49  /  ORIGEM 0','PA',14,1,0,21,355,414,23,311,'00000000','0','',0.000,0.000,0.000,'CRIADO POR AJUSTE xls',0.000,0.000,0.000,0.000,0.000,0.000,'','49',0.000,'','49',0.000,'','','',0.000,'',0.000,'','',NULL,'2023-10-26 11:08:56','1'),(9,'| CST 102  /  CFOP 5102  /  PIS e COFINS 49 * 49  /  ORIGEM 0','PA',14,1,0,14,289,414,23,311,'00000000','0','',0.000,0.000,0.000,'CRIADO POR AJUSTE xls',0.000,0.000,0.000,0.000,0.000,0.000,'','49',0.000,'','49',0.000,'','','',0.000,'',0.000,'','',NULL,'2023-10-26 11:08:56','1'),(10,'| CST 400  /  CFOP 5102  /  PIS e COFINS 07 * 07  /  ORIGEM 2','PA',14,1,0,20,289,414,23,311,'00000000','2','',0.000,0.000,0.000,'CRIADO POR AJUSTE xls',0.000,0.000,0.000,0.000,0.000,0.000,'','07',0.000,'','07',0.000,'','','',0.000,'',0.000,'','',NULL,'2023-10-26 11:13:13','1'),(11,'| CST 400  /  CFOP 5102  /  PIS e COFINS 07 * 07  /  ORIGEM 0','PA',14,1,0,20,289,414,23,311,'00000000','0','',0.000,0.000,0.000,'CRIADO POR AJUSTE xls',0.000,0.000,0.000,0.000,0.000,0.000,'','07',0.000,'','07',0.000,'','','',0.000,'',0.000,'','',NULL,'2023-10-26 11:13:13','1'),(12,'| CST 500  /  CFOP 5405  /  PIS e COFINS 49 * 49  /  ORIGEM 0','PA',14,1,0,21,355,414,23,311,'00000000','0','',0.000,0.000,0.000,'CRIADO POR AJUSTE xls',0.000,0.000,0.000,0.000,0.000,0.000,'','49',0.000,'','49',0.000,'','','',0.000,'',0.000,'','',NULL,'2023-10-26 11:13:13','1'),(13,'| CST 102  /  CFOP 5102  /  PIS e COFINS 49 * 49  /  ORIGEM 0','PA',14,1,0,14,289,414,23,311,'00000000','0','',0.000,0.000,0.000,'CRIADO POR AJUSTE xls',0.000,0.000,0.000,0.000,0.000,0.000,'','49',0.000,'','49',0.000,'','','',0.000,'',0.000,'','',NULL,'2023-10-26 11:13:13','1'),(14,'| CST 400  /  CFOP 5102  /  PIS e COFINS 07 * 07  /  ORIGEM 2','PA',14,1,0,20,289,414,23,311,'00000000','2','',0.000,0.000,0.000,'CRIADO POR AJUSTE xls',0.000,0.000,0.000,0.000,0.000,0.000,'','07',0.000,'','07',0.000,'','','',0.000,'',0.000,'','',NULL,'2023-10-26 11:13:13','1'),(15,'| CST 400  /  CFOP 5102  /  PIS e COFINS 07 * 07  /  ORIGEM 0','PA',14,1,0,20,289,414,23,311,'00000000','0','',0.000,0.000,0.000,'CRIADO POR AJUSTE xls',0.000,0.000,0.000,0.000,0.000,0.000,'','07',0.000,'','07',0.000,'','','',0.000,'',0.000,'','',NULL,'2023-10-26 11:13:13','1'),(16,'| CST 500  /  CFOP 5405  /  PIS e COFINS 49 * 49  /  ORIGEM 0','PA',14,1,0,21,355,414,23,311,'00000000','0','',0.000,0.000,0.000,'CRIADO POR AJUSTE xls',0.000,0.000,0.000,0.000,0.000,0.000,'','49',0.000,'','49',0.000,'','','',0.000,'',0.000,'','',NULL,'2023-10-26 11:13:13','1'),(17,'| CST 102  /  CFOP 5102  /  PIS e COFINS 49 * 49  /  ORIGEM 0','PA',14,1,0,14,289,414,23,311,'00000000','0','',0.000,0.000,0.000,'CRIADO POR AJUSTE xls',0.000,0.000,0.000,0.000,0.000,0.000,'','49',0.000,'','49',0.000,'','','',0.000,'',0.000,'','',NULL,'2023-10-26 11:13:13','1'),(18,'| CST 400  /  CFOP 5102  /  PIS e COFINS 07 * 07  /  ORIGEM 2','PA',14,1,0,20,289,414,23,311,'00000000','2','',0.000,0.000,0.000,'CRIADO POR AJUSTE xls',0.000,0.000,0.000,0.000,0.000,0.000,'','07',0.000,'','07',0.000,'','','',0.000,'',0.000,'','',NULL,'2023-10-26 12:04:08','1'),(19,'| CST 400  /  CFOP 5102  /  PIS e COFINS 07 * 07  /  ORIGEM 0','PA',14,1,0,20,289,414,23,311,'00000000','0','',0.000,0.000,0.000,'CRIADO POR AJUSTE xls',0.000,0.000,0.000,0.000,0.000,0.000,'','07',0.000,'','07',0.000,'','','',0.000,'',0.000,'','',NULL,'2023-10-26 12:04:08','1'),(20,'| CST 500  /  CFOP 5405  /  PIS e COFINS 49 * 49  /  ORIGEM 0','PA',14,1,0,21,355,414,23,311,'00000000','0','',0.000,0.000,0.000,'CRIADO POR AJUSTE xls',0.000,0.000,0.000,0.000,0.000,0.000,'','49',0.000,'','49',0.000,'','','',0.000,'',0.000,'','',NULL,'2023-10-26 12:04:08','1'),(21,'| CST 102  /  CFOP 5102  /  PIS e COFINS 49 * 49  /  ORIGEM 0','PA',14,1,0,14,289,414,23,311,'00000000','0','',0.000,0.000,0.000,'CRIADO POR AJUSTE xls',0.000,0.000,0.000,0.000,0.000,0.000,'','49',0.000,'','49',0.000,'','','',0.000,'',0.000,'','',NULL,'2023-10-26 12:04:08','1'),(22,'| CST 400  /  CFOP 5102  /  PIS e COFINS 07 * 07  /  ORIGEM 2','PA',14,1,0,20,289,414,23,311,'00000000','2','',0.000,0.000,0.000,'CRIADO POR AJUSTE xls',0.000,0.000,0.000,0.000,0.000,0.000,'','07',0.000,'','07',0.000,'','','',0.000,'',0.000,'','',NULL,'2023-10-26 12:06:22','1'),(23,'| CST 400  /  CFOP 5102  /  PIS e COFINS 07 * 07  /  ORIGEM 0','PA',14,1,0,20,289,414,23,311,'00000000','0','',0.000,0.000,0.000,'CRIADO POR AJUSTE xls',0.000,0.000,0.000,0.000,0.000,0.000,'','07',0.000,'','07',0.000,'','','',0.000,'',0.000,'','',NULL,'2023-10-26 12:06:22','1'),(24,'| CST 500  /  CFOP 5405  /  PIS e COFINS 49 * 49  /  ORIGEM 0','PA',14,1,0,21,355,414,23,311,'00000000','0','',0.000,0.000,0.000,'CRIADO POR AJUSTE xls',0.000,0.000,0.000,0.000,0.000,0.000,'','49',0.000,'','49',0.000,'','','',0.000,'',0.000,'','',NULL,'2023-10-26 12:06:22','1'),(25,'| CST 102  /  CFOP 5102  /  PIS e COFINS 49 * 49  /  ORIGEM 0','PA',14,1,0,14,289,414,23,311,'00000000','0','',0.000,0.000,0.000,'CRIADO POR AJUSTE xls',0.000,0.000,0.000,0.000,0.000,0.000,'','49',0.000,'','49',0.000,'','','',0.000,'',0.000,'','',NULL,'2023-10-26 12:06:22','1'),(26,'| CST 400  /  CFOP 5102  /  PIS e COFINS 07 * 07  /  ORIGEM 2','PA',14,1,0,20,289,414,23,311,'00000000','2','',0.000,0.000,0.000,'CRIADO POR AJUSTE xls',0.000,0.000,0.000,0.000,0.000,0.000,'','07',0.000,'','07',0.000,'','','',0.000,'',0.000,'','',NULL,'2023-10-26 12:06:22','1'),(27,'| CST 400  /  CFOP 5102  /  PIS e COFINS 07 * 07  /  ORIGEM 0','PA',14,1,0,20,289,414,23,311,'00000000','0','',0.000,0.000,0.000,'CRIADO POR AJUSTE xls',0.000,0.000,0.000,0.000,0.000,0.000,'','07',0.000,'','07',0.000,'','','',0.000,'',0.000,'','',NULL,'2023-10-26 12:06:22','1'),(28,'| CST 500  /  CFOP 5405  /  PIS e COFINS 49 * 49  /  ORIGEM 0','PA',14,1,0,21,355,414,23,311,'00000000','0','',0.000,0.000,0.000,'CRIADO POR AJUSTE xls',0.000,0.000,0.000,0.000,0.000,0.000,'','49',0.000,'','49',0.000,'','','',0.000,'',0.000,'','',NULL,'2023-10-26 12:06:22','1'),(29,'| CST 102  /  CFOP 5102  /  PIS e COFINS 49 * 49  /  ORIGEM 0','PA',14,1,0,14,289,414,23,311,'00000000','0','',0.000,0.000,0.000,'CRIADO POR AJUSTE xls',0.000,0.000,0.000,0.000,0.000,0.000,'','49',0.000,'','49',0.000,'','','',0.000,'',0.000,'','',NULL,'2023-10-26 12:06:22','1');
/*!40000 ALTER TABLE `grupotributacao` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-26 12:27:08
