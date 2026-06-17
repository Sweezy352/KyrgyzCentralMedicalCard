-- ============================================================
-- ДИАГНОСТИКА: посмотри реальные ID
-- ============================================================

SELECT id, inn, fio FROM users ORDER BY id;

SELECT id, name, type FROM organizations ORDER BY id;

SELECT ou.id, ou.user_id, u.fio, u.inn, ou.organization_id, ou.role, ou.is_active
FROM organization_users ou
JOIN users u ON u.id = ou.user_id
ORDER BY ou.id;

-- ============================================================
-- ИСПРАВЛЕНИЕ: добавь пользователей в organization_users
-- Замени user_id и organization_id на реальные из запросов выше
-- ============================================================

-- Добавить CLINIC_ADMIN (inn=10000000000004) в клинику (id=1)
INSERT INTO organization_users (organization_id, user_id, role, department, is_active, joined_at)
SELECT o.id, u.id, 'CLINIC_ADMIN', 'Администрация', true, NOW()
FROM organizations o, users u
WHERE o.id = 1 AND u.inn = '10000000000004'
  AND NOT EXISTS (
    SELECT 1 FROM organization_users ou WHERE ou.user_id = u.id AND ou.organization_id = o.id
  );

-- Добавить COMPANY_HR (inn=10000000000005) в работодателя (id=3)
INSERT INTO organization_users (organization_id, user_id, role, department, is_active, joined_at)
SELECT o.id, u.id, 'COMPANY_HR', 'HR отдел', true, NOW()
FROM organizations o, users u
WHERE o.id = 3 AND u.inn = '10000000000005'
  AND NOT EXISTS (
    SELECT 1 FROM organization_users ou WHERE ou.user_id = u.id AND ou.organization_id = o.id
  );

-- Добавить DOCTOR Иванова (inn=10000000000002) в клинику (id=1)
INSERT INTO organization_users (organization_id, user_id, role, department, is_active, joined_at)
SELECT o.id, u.id, 'DOCTOR', 'Кардиология', true, NOW()
FROM organizations o, users u
WHERE o.id = 1 AND u.inn = '10000000000002'
  AND NOT EXISTS (
    SELECT 1 FROM organization_users ou WHERE ou.user_id = u.id AND ou.organization_id = o.id
  );

-- Добавить DOCTOR Петрову (inn=10000000000003) в клинику (id=1)
INSERT INTO organization_users (organization_id, user_id, role, department, is_active, joined_at)
SELECT o.id, u.id, 'DOCTOR', 'Терапия', true, NOW()
FROM organizations o, users u
WHERE o.id = 1 AND u.inn = '10000000000003'
  AND NOT EXISTS (
    SELECT 1 FROM organization_users ou WHERE ou.user_id = u.id AND ou.organization_id = o.id
  );

-- ============================================================
-- ПРОВЕРКА после вставки
-- ============================================================

SELECT ou.id, u.fio, u.inn, ou.role, o.name as org_name, ou.is_active
FROM organization_users ou
JOIN users u ON u.id = ou.user_id
JOIN organizations o ON o.id = ou.organization_id
ORDER BY ou.id;
