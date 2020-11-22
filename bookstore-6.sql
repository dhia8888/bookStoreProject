-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
<<<<<<< HEAD:bookstore-5.sql
-- Généré le : Dim 22 nov. 2020 à 22:51
=======
-- Généré le : Dim 22 nov. 2020 à 23:47
>>>>>>> feature5:bookstore-6.sql
-- Version du serveur :  10.4.16-MariaDB
-- Version de PHP : 7.4.12

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
  `bio` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `date_naissance` varchar(342) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `auteur`
--

INSERT INTO `auteur` (`id`, `nom`, `prenom`, `bio`, `date_naissance`) VALUES
(1, 'Ben Gaid Hassine', 'mohamed', 'young author', '04/10/1998'),
(2, 'Kefi', 'Aymen', 'Big author', '10/06/1997');

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
(15, '22/11/2020', 0, 3);

-- --------------------------------------------------------

--
-- Structure de la table `edition`
--

CREATE TABLE `edition` (
  `id` int(11) NOT NULL,
  `adresse` varchar(342) NOT NULL,
  `pays` varchar(342) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `edition`
--

INSERT INTO `edition` (`id`, `adresse`, `pays`) VALUES
(2, 'Charguia', 'Tunisie');

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
(4, '22/11/2021', 130, 15);

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

--
-- Déchargement des données de la table `feedback`
--

INSERT INTO `feedback` (`id`, `nb_etoile`, `commentaire`, `id_user`, `livre_id`) VALUES
(2, 4, 'waw', 3, 3),
(3, 5, 'beau', 3, 2);

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
(2, 2),
(2, 3),
(3, 4),
(3, 5);

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
(15, 2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `livre`
--

CREATE TABLE `livre` (
  `id` int(11) NOT NULL,
  `edition_id` int(11) DEFAULT NULL,
  `titre_livre` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `langue` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nb_pages` int(11) NOT NULL,
  `annee` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prix` float NOT NULL,
  `nbr_exemplaires` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `livre`
--

INSERT INTO `livre` (`id`, `edition_id`, `titre_livre`, `langue`, `description`, `nb_pages`, `annee`, `prix`, `nbr_exemplaires`) VALUES
(2, 2, 'You', 'Anglais', 'iot', 102, '2020', 180, 34),
(3, 2, 'Blade', 'Anglais', 'iot', 152, '2020', 190, 14);

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
(1, 2),
(2, 3);

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
(6, 'Nouvelle commande', 'Une nouvelle commande a été ajoutée', 1),
(7, 'Nouvelle commande', 'Une nouvelle commande a été ajoutée', 1),
(8, 'Nouvelle commande', 'Une nouvelle commande a été ajoutée', 0);

-- --------------------------------------------------------

--
-- Structure de la table `panier`
--

CREATE TABLE `panier` (
  `id` int(11) NOT NULL,
  `nbrArticle` int(11) NOT NULL,
  `total` float NOT NULL,
  `commande_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `panier`
--

INSERT INTO `panier` (`id`, `nbrArticle`, `total`, `commande_id`, `client_id`) VALUES
(6, 7, 347, 15, 3);

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
<<<<<<< HEAD:bookstore-5.sql
  `matricule` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL
=======
  `matricule` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL
>>>>>>> feature5:bookstore-6.sql
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `nom`, `prenom`, `username`, `password`, `email`, `adresse`, `role`, `numinscrit`, `immatriculation`, `rib`, `salaire`, `matricule`) VALUES
<<<<<<< HEAD:bookstore-5.sql
(3, 'Dhaouadi', 'Amyne', 'AmyneDh', '0000', 'AmyneDhaouadi@esprit.tn', 'Ferryville', 'Membre', 111, NULL, NULL, NULL, '');
=======
(3, 'Dhaouadi', 'Amyne', 'AmyneDh', '0000', 'AmyneDhaouadi@esprit.tn', 'Ferryville', 'Membre', 111, NULL, NULL, NULL, ''),
(5, 'dh', 'amine', 'hammadi', '', 'password', 'ferryville@g.com', 'Admin', NULL, '123', NULL, NULL, NULL),
(6, 'ah', 'ahmed', 'ben ahmed', '', 'password', 'bizerte@g.com', 'Admin', NULL, '124', NULL, NULL, NULL),
(7, 'BGH', 'Med', 'Medbgh', '1423', 'mbgh@gmail.com', 'Bizerte', 'Membre', 111, NULL, NULL, NULL, NULL),
(8, 'Abidi', 'Temim', 'TemimB', '789', 'AbidiT@gmail.com', 'Ghazela', 'Gerant', NULL, NULL, '78945611223', 450, 'azerdj');
>>>>>>> feature5:bookstore-6.sql

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
  ADD KEY `facture_opl` (`client_id`),
  ADD KEY `FK_E6FFD7AAlljljljl` (`commande_id`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `click`
--
ALTER TABLE `click`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT pour la table `edition`
--
ALTER TABLE `edition`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `facture`
--
ALTER TABLE `facture`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `genre`
--
ALTER TABLE `genre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `livre`
--
ALTER TABLE `livre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `notification`
--
ALTER TABLE `notification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `panier`
--
ALTER TABLE `panier`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
<<<<<<< HEAD:bookstore-5.sql
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
=======
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
>>>>>>> feature5:bookstore-6.sql

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
  ADD CONSTRAINT `FK_E6FFD7AAlljljljl` FOREIGN KEY (`commande_id`) REFERENCES `ligne_commande` (`commande_id`),
  ADD CONSTRAINT `facture_opl` FOREIGN KEY (`client_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
