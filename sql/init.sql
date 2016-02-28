INSERT into tester.ACCOUNT (ID, EMAIL, PASSWORD, F_NAME, L_NAME, S_NAME) VALUES (1, 'tester@gmail.com', 'tester', 'f_tester', 'l_tester', 's_tester');

INSERT into tester.TEST (ID, NAME, DESCRIPTION) VALUES (1, 'Теппинг', 'Теппинг');
INSERT into tester.TEST (ID, NAME, DESCRIPTION) VALUES (2, 'Светофор', 'Светофор');
INSERT into tester.TEST (ID, NAME, DESCRIPTION) VALUES (3, 'Расстановка чисел', 'Расстановка чисел');

INSERT into tester.TEST_ATTR (ID, NAME, VAL, TEST_ID) VALUES (1, 'Количество нажатий за 1 интервал', 30, 1);
INSERT into tester.TEST_ATTR (ID, NAME, VAL, TEST_ID) VALUES (2, 'Количество нажатий за 2 интервал', 30, 1);
INSERT into tester.TEST_ATTR (ID, NAME, VAL, TEST_ID) VALUES (3, 'Количество нажатий за 3 интервал', 30, 1);
INSERT into tester.TEST_ATTR (ID, NAME, VAL, TEST_ID) VALUES (4, 'Количество нажатий за 4 интервал', 30, 1);
INSERT into tester.TEST_ATTR (ID, NAME, VAL, TEST_ID) VALUES (5, 'Количество нажатий за 5 интервал', 30, 1);
INSERT into tester.TEST_ATTR (ID, NAME, VAL, TEST_ID) VALUES (6, 'Количество нажатий за 6 интервал', 30, 1);
INSERT into tester.TEST_ATTR (ID, NAME, VAL, TEST_ID) VALUES (7, 'Процент ошибок', 0, 2);
INSERT into tester.TEST_ATTR (ID, NAME, VAL, TEST_ID) VALUES (8, 'Среднее время реакции', 800, 2);

INSERT into tester.TEST_ATTR (ID, NAME, VAL, TEST_ID, SORT_ORDER) VALUES (9, 'Время прохождения 1 интервала', 30, 3, 1);
INSERT into tester.TEST_ATTR (ID, NAME, VAL, TEST_ID, SORT_ORDER) VALUES (10, 'Время прохождения 2 интервала', 30, 3, 2);
INSERT into tester.TEST_ATTR (ID, NAME, VAL, TEST_ID, SORT_ORDER) VALUES (11, 'Время прохождения 3 интервала', 30, 3, 3);
INSERT into tester.TEST_ATTR (ID, NAME, VAL, TEST_ID, SORT_ORDER) VALUES (12, 'Время прохождения 4 интервала', 30, 3, 4);
INSERT into tester.TEST_ATTR (ID, NAME, VAL, TEST_ID, SORT_ORDER) VALUES (13, 'Время прохождения 5 интервала', 30, 3, 5);

INSERT into tester.TEST_ATTR (ID, NAME, VAL, TEST_ID, SORT_ORDER) VALUES (15, 'Количество ошибок за 1 интервал', 30, 0, 1);
INSERT into tester.TEST_ATTR (ID, NAME, VAL, TEST_ID, SORT_ORDER) VALUES (16, 'Количество ошибок за 2 интервал', 30, 0, 2);
INSERT into tester.TEST_ATTR (ID, NAME, VAL, TEST_ID, SORT_ORDER) VALUES (17, 'Количество ошибок за 3 интервал', 30, 0, 3);
INSERT into tester.TEST_ATTR (ID, NAME, VAL, TEST_ID, SORT_ORDER) VALUES (18, 'Количество ошибок за 4 интервал', 30, 0, 4);
INSERT into tester.TEST_ATTR (ID, NAME, VAL, TEST_ID, SORT_ORDER) VALUES (19, 'Количество ошибок за 5 интервал', 30, 0, 5);

INSERT into tester.TEST_RESULT (ID, ACCOUNT_ID, TEST_ID, RESULT_DATE) VALUES (1, 1, 1, '2016-01-24 16:55:16.213');
INSERT into tester.TEST_RESULT (ID, ACCOUNT_ID, TEST_ID, RESULT_DATE) VALUES (2, 1, 2, '2016-01-24 16:55:30.690');

INSERT into tester.TEST_RESULT_ATTR (ID, TEST_RESULT_ID, TEST_ATTR_ID, VAL) VALUES (1, 1, 1, 30);
INSERT into tester.TEST_RESULT_ATTR (ID, TEST_RESULT_ID, TEST_ATTR_ID, VAL) VALUES (2, 1, 2, 31);
INSERT into tester.TEST_RESULT_ATTR (ID, TEST_RESULT_ID, TEST_ATTR_ID, VAL) VALUES (3, 1, 3, 32);
INSERT into tester.TEST_RESULT_ATTR (ID, TEST_RESULT_ID, TEST_ATTR_ID, VAL) VALUES (4, 1, 4, 33);
INSERT into tester.TEST_RESULT_ATTR (ID, TEST_RESULT_ID, TEST_ATTR_ID, VAL) VALUES (5, 1, 5, 34);
INSERT into tester.TEST_RESULT_ATTR (ID, TEST_RESULT_ID, TEST_ATTR_ID, VAL) VALUES (6, 1, 6, 35);
INSERT into tester.TEST_RESULT_ATTR (ID, TEST_RESULT_ID, TEST_ATTR_ID, VAL) VALUES (7, 2, 7, 20);
INSERT into tester.TEST_RESULT_ATTR (ID, TEST_RESULT_ID, TEST_ATTR_ID, VAL) VALUES (8, 2, 8, 800);