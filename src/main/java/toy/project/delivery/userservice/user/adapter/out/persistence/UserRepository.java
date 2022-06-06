package toy.project.delivery.userservice.user.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<UserJpaEntity, Long> {
    UserJpaEntity findByEmail(String email);
}
