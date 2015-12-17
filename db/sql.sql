
SELECT * FROM t_next_collect;

SELECT * FROM t_next_collect ORDER BY (productsum+collectsum) DESC LIMIT 0, 5;

SELECT * FROM t_next_user;

update t_next_user set collectpermission = 1;

