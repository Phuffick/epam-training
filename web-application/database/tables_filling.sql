INSERT INTO `hospital_db`.`patient`
(`id`, `name`)
VALUES
(1,    'Jack Smith'),
(2,    'George Jones'),
(3,    'Amelia Morton'),
(4,    'Thomas Wilson'),
(5,    'Jennifer Brown');

INSERT INTO `hospital_db`.`medical_card`
(`id`, `admission_date`, `discharge_date`, `patient_id`)
VALUES
(1,    '2021-05-10',		 NULL, 1),
(2,    '2021-05-21',		 NULL, 2),
(3,    '2021-04-18', '2021-05-18', 3),
(4,    '2021-04-9',  '2021-04-14', 4),
(5,    '2021-03-15',  '2021-04-8', 5);

INSERT INTO `hospital_db`.`user`
(`id`, `login`,         `password`, `role`)
VALUES
(1,    'nurse_Maria',   '12345',     2),
(2,    'nurse_Julet',   '12345',     2),
(3,    'doctor_Bob',  	'12345',     1),
(4,    'doctor_Edward', '12345',     1),
(5,    'admin',   		'12345',     0);

INSERT INTO `hospital_db`.`doctor`
(`name`, 		  `id`)
VALUES
('doctor_Bob', 	  3),
('doctor_Edward', 4);

INSERT INTO `hospital_db`.`diagnosis`
(`id`, `description`, `date`, `doctor_id`, `medical_card_id`)
VALUES
(1, 'acute bronchitis', '2021-05-11', 3, 1),
(2, 'quinsy', 			'2021-05-17', 4, 3),
(3, 'pneumonia',	 	'2021-04-11', 3, 4),
(4, 'tuberculosis', 	'2021-03-20', 4, 5);

INSERT INTO `hospital_db`.`procedure`
(`id`, `name`, `required_count`, `required_count_consumed`, `date`, `medical_card_id`)
VALUES
(1, 'massage', 		 3, 0, '2021-05-10', 1),
(2, 'physiotherapy', 5, 0, '2021-04-10', 4),
(3, 'physiotherapy', 6, 0, '2021-03-20', 5);

INSERT INTO `hospital_db`.`surgery`
(`id`, `name`, `planing_date`, `is_done`, `medical_card_id`, `doctor_id`)
VALUES
(1, 'removal of an abscess', '2021-05-16', false, 1, 3),
(2, 'removal of an abscess', '2021-03-18', true,  5, 4);

INSERT INTO `hospital_db`.`medications`
(`id`, `name`, `is_done`, `required_count`, `amount_per_day`, `date`, `medical_card_id`)
VALUES
(1, 'albuterol', 	false,  60,  2, '2021-05-10', 1),
(2, 'amoxicillin', true,   40,  1, '2021-05-10', 3),
(3, 'pneumonia',	true,  100,  4, '2021-04-10', 4),
(4, 'prednisone', 	true,   90,  3, '2021-03-20', 5);
