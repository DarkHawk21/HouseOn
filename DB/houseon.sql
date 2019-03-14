-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-10-2018 a las 20:17:21
-- Versión del servidor: 10.1.13-MariaDB
-- Versión de PHP: 5.6.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `houseon`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `login`
--

CREATE TABLE `login` (
  `codUser` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `appat` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `apmat` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `user` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `pass` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `access` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `login`
--

INSERT INTO `login` (`codUser`, `name`, `appat`, `apmat`, `user`, `pass`, `access`) VALUES
(1, 'Enrique', 'Carranza', 'Balderas', 'Condor99', 'prueba', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `statusdispositivos`
--

CREATE TABLE `statusdispositivos` (
  `codDisp` int(11) NOT NULL,
  `nameDisp` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `placeDisp` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `statusDisp` varchar(15) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `statusdispositivos`
--

INSERT INTO `statusdispositivos` (`codDisp`, `nameDisp`, `placeDisp`, `statusDisp`) VALUES
(1, 'LED', 'Cuarto Morado', 'b'),
(2, 'LED', 'Baño', 'd'),
(3, 'LED', 'Cuarto Anaranjado', 'f'),
(4, 'VENTILADOR', 'Cuarto grande', 'h');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`codUser`);

--
-- Indices de la tabla `statusdispositivos`
--
ALTER TABLE `statusdispositivos`
  ADD PRIMARY KEY (`codDisp`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `login`
--
ALTER TABLE `login`
  MODIFY `codUser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `statusdispositivos`
--
ALTER TABLE `statusdispositivos`
  MODIFY `codDisp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
