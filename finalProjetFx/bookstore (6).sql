-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 11 déc. 2020 à 23:24
-- Version du serveur :  10.4.17-MariaDB
-- Version de PHP : 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bookstore`
--

-- --------------------------------------------------------

--
-- Structure de la table `auteur`
--

CREATE TABLE `auteur` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `bio` varchar(444) COLLATE utf8mb4_unicode_ci NOT NULL,
  `date_naissance` varchar(342) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `auteur`
--

INSERT INTO `auteur` (`id`, `nom`, `prenom`, `bio`, `date_naissance`) VALUES
(3, 'Mark ', 'Morford', ' Professor Mark P. O. Morford, of Leeds, died Monday October 14, 2019 at the Hospice of the Fisher Home after a brief illness', '01/12/1929'),
(4, 'Richard \r\n', 'B. Wright', 'Richard Bruce Wright CM was a Canadian novelist. He was known for his break-through 2001 novel Clara Callan, which won three major literary awards in Canada:', '03/3/1937'),
(5, 'Carlo ', 'DEste', 'Carlo D\'Este was an American military historian and biographer, author of several books, especially on World War II. He was a decorated U.S. Army lieutenant colonel. In 2011, he was awarded the Pritzker Literature Award for Lifetime Achievement in Militar', '01/01/1936 '),
(6, 'Elizabeth', 'Wayland Barber', 'Elizabeth Jane \"Betchen\" Wayland Barber is an American scholar and expert on archaeology, linguistics, textiles, and folk dance as well as Professor emerita of archaeology and linguistics at Occidental College', '11/12/1940'),
(7, 'Amy \r\n', 'Tan', 'Amy Ruth Tan is an American author known for the novel The Joy Luck Club, which was adapted into the film The Joy Luck Club in 1993 by director Wayne Wang', '12/06/1974'),
(8, 'Robert ', 'Cowley ', 'Robert Cowley is an American military historian, who writes on topics in American and European military history ranging from the Civil War through World War II. He has held several senior positions in book and magazine publishing and is the founding edito', '1925/11/10'),
(9, 'Abou Kacem', 'al chebi', '', '2020-12-10');

-- --------------------------------------------------------

--
-- Structure de la table `click`
--

CREATE TABLE `click` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `livre_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `id` int(11) NOT NULL,
  `date_com` varchar(321) COLLATE utf8mb4_unicode_ci NOT NULL,
  `livraison` tinyint(1) NOT NULL,
  `client_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`id`, `date_com`, `livraison`, `client_id`) VALUES
(15, '12/10/2009', 1, 3),
(17, '12/12/2020', 0, 3),
(18, '12/12/2007', 0, 3),
(19, '2020/12/11', 1, 3),
(20, '12/12/2007', 0, 3),
(21, '14/12/2007', 1, 3),
(22, '12/12/2009', 0, 3),
(23, '12/12/2007', 0, 3),
(24, '12/12/2009', 1, 3),
(25, '12/12/2007', 0, 3),
(26, '12/12/2020', 0, 3),
(27, '12/12/2008', 1, 3),
(28, '12/12/2004', 1, 3),
(29, '24/12/2002', 1, 3),
(31, '12/12/2000', 0, 3),
(32, '12/12/2008', 0, 3),
(33, '12/12/2004', 1, 3),
(35, '22/11/2020', 0, 3),
(40, '09/12/2020', 1, 3),
(43, '09/12/2020', 1, 3),
(44, '09/12/2020', 1, 3),
(48, '11/12/2020', 1, 3),
(52, '2020/12/11', 1, 3),
(53, '2020/12/11', 1, 3),
(54, '2020/12/11', 1, 3),
(55, '10/12/2020', 1, 3),
(56, '2020/12/11', 1, 3),
(57, '2020/12/11', 1, 3),
(58, '2020/12/11', 1, 3),
(59, '2020/12/11', 1, 3),
(60, '2020/12/11', 1, 3),
(61, '2020/12/11', 1, 3),
(62, '2020/12/11', 1, 3),
(64, '2020/12/11', 1, 3),
(65, '2020/12/11', 1, 3),
(66, '2020/12/11', 1, 3),
(67, '2020/12/11', 1, 3),
(68, '2020/12/11', 1, 3),
(71, '2020/12/11', 1, 3),
(72, '2020/12/11', 1, 3),
(73, '2020/12/11', 1, 3),
(74, '2020/12/11', 1, 3),
(75, '2020/12/11', 1, 3),
(76, '2020/12/11', 1, 3),
(77, '2020/12/11', 1, 3),
(78, '2020/12/11', 1, 3);

-- --------------------------------------------------------

--
-- Structure de la table `edition`
--

CREATE TABLE `edition` (
  `id` int(11) NOT NULL,
  `adresse` varchar(342) NOT NULL,
  `pays` varchar(342) NOT NULL,
  `longitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `edition`
--

INSERT INTO `edition` (`id`, `adresse`, `pays`, `longitude`, `latitude`) VALUES
(2, 'Charguia', 'Tunisie', 36.8533309, 10.2072943),
(3, 'Oxford University Press', 'us', 37.3005336, 9.7830671),
(4, 'HarperFlamingo Canada', 'Canada', 36.8978461, 10.1876042),
(5, 'HarperPerennial', 'uk', 36.8096326, 10.1354992),
(6, 'ibnousina', 'tunis', 36.8533309, 10.2072943);

-- --------------------------------------------------------

--
-- Structure de la table `facture`
--

CREATE TABLE `facture` (
  `id` int(11) NOT NULL,
  `date_fact` varchar(234) COLLATE utf8mb4_unicode_ci NOT NULL,
  `montant` float NOT NULL,
  `commande_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `facture`
--

INSERT INTO `facture` (`id`, `date_fact`, `montant`, `commande_id`) VALUES
(4, '2013/4/12', 130, 15),
(5, '2014/5/12', 345, 26),
(6, '2014/7/13', 21.8, 27),
(7, '2013/9/09', 123, 25),
(8, '2013/07/09', 34.5, 33),
(9, '2013/05/04', 456, 21),
(10, '2013/09/03', 453, 24),
(12, '2013/02/02', 235, 29),
(18, '2020/12/10', 34, 25),
(19, '2020/12/10', 34, 20),
(20, '2020/12/10', 3468, 19),
(21, '2020/12/10', 3234, 24),
(22, '2020/12/10', 3234, 24),
(23, '2020/12/10', 234, 25),
(24, '2020/12/10', 234, 25),
(25, '2020/12/11', 1070, 48),
(26, '2020/12/11', 900, 54),
(27, '2020/12/11', 720, 55),
(28, '2020/12/11', 1080, 56);

-- --------------------------------------------------------

--
-- Structure de la table `feedback`
--

CREATE TABLE `feedback` (
  `id` int(11) NOT NULL,
  `nb_etoile` int(11) NOT NULL,
  `commentaire` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_user` int(11) NOT NULL,
  `livre_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `genre`
--

CREATE TABLE `genre` (
  `id` int(11) NOT NULL,
  `nom_genre` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `genre`
--

INSERT INTO `genre` (`id`, `nom_genre`) VALUES
(2, 'Romance'),
(3, 'Action'),
(4, 'Drama'),
(5, 'Horror');

-- --------------------------------------------------------

--
-- Structure de la table `genre_livre`
--

CREATE TABLE `genre_livre` (
  `livre_id` int(11) NOT NULL,
  `genre_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `genre_livre`
--

INSERT INTO `genre_livre` (`livre_id`, `genre_id`) VALUES
(3, 4),
(3, 5),
(4, 3),
(8, 7);

-- --------------------------------------------------------

--
-- Structure de la table `ligne_commande`
--

CREATE TABLE `ligne_commande` (
  `commande_id` int(11) NOT NULL,
  `livre_id` int(11) NOT NULL,
  `quantite` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `ligne_commande`
--

INSERT INTO `ligne_commande` (`commande_id`, `livre_id`, `quantite`) VALUES
(15, 3, 2),
(17, 4, 2),
(18, 5, 2),
(19, 2, 3),
(20, 5, 6),
(21, 7, 4),
(22, 5, 7),
(23, 2, 8),
(25, 4, 4),
(77, 2, 5),
(78, 2, 5),
(78, 3, 7);

-- --------------------------------------------------------

--
-- Structure de la table `livre`
--

CREATE TABLE `livre` (
  `id` int(11) NOT NULL,
  `edition_id` int(11) DEFAULT NULL,
  `titre_livre` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `langue` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nb_pages` int(11) DEFAULT NULL,
  `annee` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `prix` float DEFAULT NULL,
  `nbr_exemplaires` int(11) DEFAULT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `livre`
--

INSERT INTO `livre` (`id`, `edition_id`, `titre_livre`, `langue`, `description`, `nb_pages`, `annee`, `prix`, `nbr_exemplaires`, `image`) VALUES
(2, 2, 'You', 'English', 'Field Marshal Montgomery\'s battleplan for Normandy, following the D-Day landings on 6 June 1944, resulted in one of the most controversial campaigns of the Second World War. Carlo D\'Este\'s acclaimed book gives the fullest possible account of the conceptio', 102, '2020', 180, 14, 'http://images.amazon.com/images/P/0060973129.01.LZZZZZZZ.jpg'),
(3, 2, 'Blade', 'English', 'Field Marshal Montgomery\'s battleplan for Normandy, following the D-Day landings on 6 June 1944, resulted in one of the most controversial campaigns of the Second World War. Carlo D\'Este\'s acclaimed book gives the fullest possible account of the conceptio', 152, '2020', 190, 7, 'http://images.amazon.com/images/P/0195153448.01.MZZZZZZZ.jpg'),
(4, 3, 'Classical Mythology', 'English', 'Field Marshal Montgomery\'s battleplan for Normandy, following the D-Day landings on 6 June 1944, resulted in one of the most controversial campaigns of the Second World War. Carlo D\'Este\'s acclaimed book gives the fullest possible account of the conceptio', 33, '2002', 34, 95, 'http://images.amazon.com/images/P/0195153448.01.THUMBZZZ.jpg'),
(5, 3, 'Clara Callan', 'English', 'Field Marshal Montgomery\'s battleplan for Normandy, following the D-Day landings on 6 June 1944, resulted in one of the most controversial campaigns of the Second World War. Carlo D\'Este\'s acclaimed book gives the fullest possible account of the conceptio', 33, '2001', 123, 1234, 'http://images.amazon.com/images/P/0002005018.01.THUMBZZZ.jpg'),
(6, 4, 'Decision in Normandy', 'English', 'Field Marshal Montgomery\'s battleplan for Normandy, following the D-Day landings on 6 June 1944, resulted in one of the most controversial campaigns of the Second World War. Carlo D\'Este\'s acclaimed book gives the fullest possible account of the conceptio', 33, '1991', 12, 100, 'http://images.amazon.com/images/P/0060973129.01.LZZZZZZZ.jpg'),
(7, 2, 'The Kitchen God\'s Wife', 'English ', 'Field Marshal Montgomery\'s battleplan for Normandy, following the D-Day landings on 6 June 1944, resulted in one of the most controversial campaigns of the Second World War. Carlo D\'Este\'s acclaimed book gives the fullest possible account of the conceptio', 33, '1992', 34, 123, 'http://images.amazon.com/images/P/0399135782.01.THUMBZZZ.jpg'),
(8, 6, 'zzz', 'arabi', 'Field Marshal Montgomery\'s battleplan for Normandy, following the D-Day landings on 6 June 1944, resulted in one of the most controversial campaigns of the Second World War. Carlo D\'Este\'s acclaimed book gives the fullest possible account of the conceptio', 34, '1234', 34, 27, '0 (2).jpg');

-- --------------------------------------------------------

--
-- Structure de la table `livre_auteur`
--

CREATE TABLE `livre_auteur` (
  `id_auteur` int(11) NOT NULL,
  `id_livre` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `livre_auteur`
--

INSERT INTO `livre_auteur` (`id_auteur`, `id_livre`) VALUES
(7, 3),
(6, 4),
(5, 3),
(7, 6),
(9, 8);

-- --------------------------------------------------------

--
-- Structure de la table `notification`
--

CREATE TABLE `notification` (
  `id` int(11) NOT NULL,
  `type` varchar(345) NOT NULL,
  `message` varchar(355) NOT NULL,
  `vu` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `notification`
--

INSERT INTO `notification` (`id`, `type`, `message`, `vu`) VALUES
(5, 'payment en ligne', 'Un payement à était effectué enligne\navec un montant de: 3420.0 $', 1),
(6, 'Command sur place', 'Commande num: 66 est effectuée !', 1),
(7, 'Command sur place', 'Commande num: 67 est effectuée !', 1),
(8, 'payment en ligne', 'Un payement à était effectué enligne\navec un montant de: 268.0 $', 1),
(9, 'payment en ligne', 'Un payement à était effectué enligne\navec un montant de: 1994.0 $', 1),
(10, 'payment en ligne', 'Un payement à était effectué enligne\navec un montant de: 1260.0 $', 1),
(11, 'payment en ligne', 'Un payement à était effectué enligne\navec un montant de: 1498.0 $', 1),
(12, 'payment en ligne', 'Un payement à était effectué enligne\navec un montant de: 1800.0 $', 1),
(13, 'payment en ligne', 'Un payement à était effectué enligne\navec un montant de: 900.0 $', 1),
(14, 'Command sur place', 'Commande num: 75 est effectuée !', 1),
(15, 'payment en ligne', 'Un payement à était effectué enligne\navec un montant de: 1970.0 $', 0),
(16, 'Command sur place', 'Commande num: 76 est effectuée !', 0),
(17, 'Command sur place', 'Commande num: 77 est effectuée !', 0),
(18, 'payment en ligne', 'Un payement à était effectué enligne\navec un montant de: 2230.0 $', 0);

-- --------------------------------------------------------

--
-- Structure de la table `panier`
--

CREATE TABLE `panier` (
  `id` int(11) NOT NULL,
  `titreLiv` varchar(255) NOT NULL,
  `nbrArticle` int(11) NOT NULL,
  `total` float NOT NULL,
  `client_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `adresse` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `numinscrit` int(11) DEFAULT NULL,
  `immatriculation` varchar(342) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `rib` varchar(342) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `salaire` double DEFAULT NULL,
  `matricule` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `nom`, `prenom`, `username`, `password`, `email`, `adresse`, `role`, `numinscrit`, `immatriculation`, `rib`, `salaire`, `matricule`) VALUES
(3, 'Dhaouadi', 'Amyne', 'AmyneDh', '0000', 'AmyneDhaouadi@esprit.tn', 'Ferryville', 'Membre', 111, NULL, NULL, NULL, ''),
(4, 'dhia', 'Hannachi', 'dha', 'azerty', 'hama@hama.com', 'new york', 'Membre', NULL, NULL, NULL, NULL, NULL),
(9, 'Ayoub', 'bouslimi', 'AYB', 'qwerty', 'dhia@yahoo.com', 'sdfsfsfs', 'Admin', NULL, NULL, NULL, NULL, 'sdsfsfsfsfsfsfs');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `auteur`
--
ALTER TABLE `auteur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `click`
--
ALTER TABLE `click`
  ADD PRIMARY KEY (`id`),
  ADD KEY `facture_ibfl` (`livre_id`),
  ADD KEY `FK_E6FFD7AA8oikin2E54` (`user_id`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`id`),
  ADD KEY `facture_ibfl5` (`client_id`);

--
-- Index pour la table `edition`
--
ALTER TABLE `edition`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `facture`
--
ALTER TABLE `facture`
  ADD PRIMARY KEY (`id`),
  ADD KEY `facture_ibflmmpojoljlj` (`commande_id`);

--
-- Index pour la table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_y` (`id_user`),
  ADD KEY `facture_ibfl5gpojnj` (`livre_id`);

--
-- Index pour la table `genre`
--
ALTER TABLE `genre`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `genre_livre`
--
ALTER TABLE `genre_livre`
  ADD KEY `FK_GNRLVR111` (`genre_id`),
  ADD KEY `FK_LVRGNR2222` (`livre_id`);

--
-- Index pour la table `ligne_commande`
--
ALTER TABLE `ligne_commande`
  ADD PRIMARY KEY (`commande_id`,`livre_id`),
  ADD KEY `FK_E6FFD7AA82ZEA2E54` (`livre_id`);

--
-- Index pour la table `livre`
--
ALTER TABLE `livre`
  ADD PRIMARY KEY (`id`),
  ADD KEY `facture_oplpppppppppppp` (`edition_id`);

--
-- Index pour la table `livre_auteur`
--
ALTER TABLE `livre_auteur`
  ADD KEY `facture_ibfl5go` (`id_livre`),
  ADD KEY `FK_E6FFD72E54` (`id_auteur`);

--
-- Index pour la table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `panier`
--
ALTER TABLE `panier`
  ADD PRIMARY KEY (`id`),
  ADD KEY `facture_opl` (`client_id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `auteur`
--
ALTER TABLE `auteur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `click`
--
ALTER TABLE `click`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=79;

--
-- AUTO_INCREMENT pour la table `edition`
--
ALTER TABLE `edition`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `facture`
--
ALTER TABLE `facture`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT pour la table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `genre`
--
ALTER TABLE `genre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `livre`
--
ALTER TABLE `livre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `notification`
--
ALTER TABLE `notification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT pour la table `panier`
--
ALTER TABLE `panier`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=94;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `click`
--
ALTER TABLE `click`
  ADD CONSTRAINT `FK_E6FFD7AA8oikin2E54` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `facture_ibfl` FOREIGN KEY (`livre_id`) REFERENCES `livre` (`id`);

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `facture_ibfl5` FOREIGN KEY (`client_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `facture`
--
ALTER TABLE `facture`
  ADD CONSTRAINT `facture_ibflmmpojoljlj` FOREIGN KEY (`commande_id`) REFERENCES `commande` (`id`);

--
-- Contraintes pour la table `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `facture_ibfl5gpojnj` FOREIGN KEY (`livre_id`) REFERENCES `livre` (`id`),
  ADD CONSTRAINT `fk_y` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `ligne_commande`
--
ALTER TABLE `ligne_commande`
  ADD CONSTRAINT `FK_E6FFD7AA82ZEA2E54` FOREIGN KEY (`livre_id`) REFERENCES `livre` (`id`),
  ADD CONSTRAINT `bigbub` FOREIGN KEY (`commande_id`) REFERENCES `commande` (`id`);

--
-- Contraintes pour la table `livre`
--
ALTER TABLE `livre`
  ADD CONSTRAINT `facture_oplpppppppppppp` FOREIGN KEY (`edition_id`) REFERENCES `edition` (`id`);

--
-- Contraintes pour la table `livre_auteur`
--
ALTER TABLE `livre_auteur`
  ADD CONSTRAINT `FK_E6FFD72E54` FOREIGN KEY (`id_auteur`) REFERENCES `auteur` (`id`),
  ADD CONSTRAINT `facture_ibfl5go` FOREIGN KEY (`id_livre`) REFERENCES `livre` (`id`);

--
-- Contraintes pour la table `panier`
--
ALTER TABLE `panier`
  ADD CONSTRAINT `facture_opl` FOREIGN KEY (`client_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
