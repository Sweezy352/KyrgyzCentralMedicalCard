-- Проверяем организации
SELECT id, name, type FROM organizations ORDER BY id;

-- Вставляем всех сотрудников с правильными user_id
-- CLINIC_ADMIN Нурланов (user_id=4) -> клиника (id=1)
INSERT INTO organization_users (organization_id, user_id, role, department, is_active, joined_at)
VALUES (1, 4, 'CLINIC_ADMIN', 'Администрация', true, NOW())
ON CONFLICT DO NOTHING;

-- COMPANY_HR Асанова (user_id=5) -> работодатель (id=3)
INSERT INTO organization_users (organization_id, user_id, role, department, is_active, joined_at)
VALUES (3, 5, 'COMPANY_HR', 'HR отдел', true, NOW())
ON CONFLICT DO NOTHING;

-- DOCTOR Иванов (user_id=2) -> клиника (id=1)
INSERT INTO organization_users (organization_id, user_id, role, department, is_active, joined_at)
VALUES (1, 2, 'DOCTOR', 'Кардиология', true, NOW())
ON CONFLICT DO NOTHING;

-- DOCTOR Петрова (user_id=3) -> клиника (id=1)
INSERT INTO organization_users (organization_id, user_id, role, department, is_active, joined_at)
VALUES (1, 3, 'DOCTOR', 'Терапия', true, NOW())
ON CONFLICT DO NOTHING;

-- Проверка
SELECT ou.id, u.id as user_id, u.fio, u.inn, ou.role, o.name as org_name, ou.is_active
FROM organization_users ou
JOIN users u ON u.id = ou.user_id
JOIN organizations o ON o.id = ou.organization_id
ORDER BY ou.id;
